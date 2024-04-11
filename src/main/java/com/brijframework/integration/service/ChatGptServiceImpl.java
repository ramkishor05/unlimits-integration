package com.brijframework.integration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brijframework.integration.model.ChatRequest;
import com.brijframework.integration.model.ChatResponse;
import com.brijframework.integration.model.ImageRequest;
import com.brijframework.integration.model.Model;

@Service
public class ChatGptServiceImpl implements ChatGptService {

	@Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.urlmodels}")
    private String urlmodels;
    
    @Value("${openai.api.urlchat}")
    private String urlchat;
    
    @Value("${openai.api.urlaudio}")
    private String urlaudio;
    
    @Value("${openai.api.urlimages}")
    private String urlimages;
    
    
    @Override
    public ChatResponse chat(String prompt) {
        ChatRequest request = new ChatRequest(model, prompt);
		return restTemplate.postForObject(urlchat,request, ChatResponse.class);
    }
    
    @Override
    public Model model() {
		return restTemplate.getForObject(urlmodels, Model.class);
    }
    
    @Override
    public String audio() {
		return restTemplate.getForObject(urlaudio, String.class);
    }
    
    @Override
    public String imagesGenerations(String prompt) {
    	ImageRequest request = new ImageRequest(model, prompt);
		return restTemplate.postForObject(urlimages+"/generations",request, String.class);
    }
    
    @Override
    public String imagesEdit(String prompt) {
    	ImageRequest request = new ImageRequest(model, prompt);
		return restTemplate.postForObject(urlimages+"/edits",request, String.class);
    }
}
