package com.brijframework.integration;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class IntegrationApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(IntegrationApplication.class, args);
		/*
		HttpClient httpClient= HttpClient.newHttpClient();
		String body=new String("{\r\n"
				+ "     \"model\": \"gpt-4o-mini\",\r\n"
				+ "     \"messages\": [{\"role\": \"user\", \"content\": \"Say this is a test!\"}],\r\n"
				+ "     \"temperature\": 0.7\r\n"
				+ "   }");
		HttpRequest request=HttpRequest.newBuilder(URI.create("https://api.openai.com/v1/chat/completions"))
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer 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")
				.POST(HttpRequest.BodyPublishers.ofString(body)).build();
		HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
		System.out.println(httpResponse.body());*/
	}

}
