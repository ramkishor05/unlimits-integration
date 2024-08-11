package com.brijframework.integration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.Model;
import com.brijframework.integration.service.ChatGptMediaService;

@RestController
@RequestMapping("/api/chatgpt/media")
public class ChatGptMediaController {
    
    
    @Autowired
    private ChatGptMediaService chatGptMediaService;
    
    @PostMapping(value="/text", consumes = {MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public String chat(@RequestBody String prompt) {
        return chatGptMediaService.text(prompt);
    }
    
    @GetMapping("/model")
    public Model model() {
        return chatGptMediaService.model();
    }
    
    @GetMapping("/images")
    public String images(@RequestParam String prompt) {
        return chatGptMediaService.imagesGenerations(prompt);
    }
    
    @GetMapping("/audio")
    public String audio() {
        return chatGptMediaService.audio();
    }
}