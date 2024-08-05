package com.brijframework.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.ChatResponse;
import com.brijframework.integration.model.Model;
import com.brijframework.integration.service.ChatGptDriveService;

@RestController
@RequestMapping("/api/chatgpt/drive")
public class ChatGptDriveController {
    
    
    @Autowired
    private ChatGptDriveService chatGptDriveService;
    
    @GetMapping("/chat/completions")
    public ChatResponse chat(@RequestParam String prompt) {
        return chatGptDriveService.chat(prompt);
    }
    
    @GetMapping("/chat/model")
    public Model model() {
        return chatGptDriveService.model();
    }
    
    @GetMapping("/chat/images")
    public String images(@RequestParam String prompt) {
        return chatGptDriveService.imagesGenerations(prompt);
    }
    
    @GetMapping("/chat/audio")
    public String audio() {
        return chatGptDriveService.audio();
    }
}