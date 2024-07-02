package com.brijframework.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.ChatResponse;
import com.brijframework.integration.model.Model;
import com.brijframework.integration.service.ChatGptService;

@RestController
@RequestMapping("/api/chatgpt/media")
public class ChatGptController {
    
    
    @Autowired
    private ChatGptService chatGptService;
    
    @GetMapping("/chat/completions")
    public ChatResponse chat(@RequestParam String prompt) {
        return chatGptService.chat(prompt);
    }
    
    @GetMapping("/chat/model")
    public Model model() {
        return chatGptService.model();
    }
    
    @GetMapping("/chat/images")
    public String images(@RequestParam String prompt) {
        return chatGptService.imagesGenerations(prompt);
    }
    
    @GetMapping("/chat/audio")
    public String audio() {
        return chatGptService.audio();
    }
}