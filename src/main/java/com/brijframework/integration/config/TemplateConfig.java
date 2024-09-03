package com.brijframework.integration.config;

import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.brijframework.integration.exceptions.RestErrorHandler;
import com.brijframework.integration.interceptors.LoggingRequestInterceptor;

@Configuration
public class TemplateConfig {

	@Value("${openai.api.key}")
	private String openaiApiKey;

	@Value("${openai.org_id}")
	private String openaiOrgId;

	@Value("${openai.prg_id}")
	private String openaiPrgId;

	@Value("${pexels.api.key}")
	private String pexelsApiKey;
	
	@Bean
	public RequestConfig requestConfig() {
		return RequestConfig.custom().setConnectionRequestTimeout(Timeout.ofMicroseconds(5000)).build();
	}

	@Bean
	public CloseableHttpClient httpClient(RequestConfig requestConfig) {
		return HttpClientBuilder.create()
				.setDefaultRequestConfig(requestConfig).evictExpiredConnections()
				.evictIdleConnections(TimeValue.of(5000, TimeUnit.MILLISECONDS))
				.setRetryStrategy(new RetryOverHttpError())
				.setConnectionManager(poolingHttpClientConnectionManager()).build();
	}

	public class RetryOverHttpError extends DefaultHttpRequestRetryStrategy {

		@Override
		public boolean retryRequest(org.apache.hc.core5.http.HttpResponse response, int execCount,
				HttpContext context) {
			if (execCount > 5) {
				return false;
			}
			return true;
		}
		
	}

	@Bean
	public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		return connectionManager;
	}

	@Bean
	@Qualifier("openaiRestTemplate")
	public RestTemplate openaiRestTemplate(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Content-Type", "application/json");
			request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
			// request.getHeaders().add("OpenAI-Organization", openaiOrgId);
			// request.getHeaders().add("OpenAI-Project", openaiPrgId);
			return execution.execute(request, body);
		});
		// restTemplate.setErrorHandler(new RestErrorHandler());
		return restTemplate;
	}

	@Bean
	@Qualifier("pexelsRestTemplate")
	public RestTemplate pexelsRestTemplate(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", pexelsApiKey);
			return execution.execute(request, body);
		});
		restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
		restTemplate.setErrorHandler(new RestErrorHandler());
		return restTemplate;
	}

	@Bean
	@Qualifier("allRestTemplate")
	public RestTemplate allRestTemplate(HttpClient httpClient) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getInterceptors().add((request, body, execution) -> {
			// request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
			return execution.execute(request, body);
		});
		restTemplate.setErrorHandler(new RestErrorHandler());
		return restTemplate;
	}
}