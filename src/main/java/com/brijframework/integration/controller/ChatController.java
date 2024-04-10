package com.brijframework.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.ChatResponse;
import com.brijframework.integration.service.ChatService;

@RestController
public class ChatController {
    
    
    @Autowired
    private ChatService chatService;
    
    @GetMapping("/chat")
    public ChatResponse chat(@RequestParam String prompt) {
        return chatService.chat(prompt);
    }
}