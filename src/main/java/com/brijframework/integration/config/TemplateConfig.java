package com.brijframework.integration.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Qualifier("openaiRestTemplate")
    public RestTemplate openaiRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
        	request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
           // request.getHeaders().add("OpenAI-Organization", openaiOrgId);
           // request.getHeaders().add("OpenAI-Project", openaiPrgId);
            return execution.execute(request, body);
        });
        //restTemplate.setErrorHandler(new RestErrorHandler());
        return restTemplate;
    }
    
    
    
    @Bean
    @Qualifier("pexelsRestTemplate")
    public RestTemplate pexelsRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization",  pexelsApiKey);
            return execution.execute(request, body);
        });
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
        restTemplate.setErrorHandler(new RestErrorHandler());
        return restTemplate;
    }
    
    @Bean
    @Qualifier("allRestTemplate")
    public RestTemplate allRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
          //  request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            return execution.execute(request, body);
        });
        restTemplate.setErrorHandler(new RestErrorHandler());
        return restTemplate;
    }
}