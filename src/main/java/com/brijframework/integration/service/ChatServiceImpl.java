package com.brijframework.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brijframework.integration.model.ChatRequest;
import com.brijframework.integration.model.ChatResponse;

@Service
public class ChatServiceImpl implements ChatService {

	@Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @Override
    public ChatResponse chat(String prompt) {
        ChatRequest request = new ChatRequest(model, prompt);
		return restTemplate.postForObject(apiUrl, request, ChatResponse.class);
    }
    
}
