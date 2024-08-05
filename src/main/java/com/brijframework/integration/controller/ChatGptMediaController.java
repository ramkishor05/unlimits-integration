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
@RequestMapping("/api/chatgpt/media")
public class ChatGptMediaController {
    
    
    @Autowired
    private ChatGptDriveService chatGptDriveService;
    
    @GetMapping("/text")
    public ChatResponse chat(@RequestParam String prompt) {
        return chatGptDriveService.chat(prompt);
    }
    
    @GetMapping("/model")
    public Model model() {
        return chatGptDriveService.model();
    }
    
    @GetMapping("/images")
    public String images(@RequestParam String prompt) {
        return chatGptDriveService.imagesGenerations(prompt);
    }
    
    @GetMapping("/audio")
    public String audio() {
        return chatGptDriveService.audio();
    }
}