package com.brijframework.integration.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brijframework.integration.service.GoogleDriveService;

@RestController
public class GoogleDriveController {

    @Autowired
    GoogleDriveService googleDriveService;


    @GetMapping("/")
    public  String sample() throws IOException, GeneralSecurityException{
        return googleDriveService.getAllAudio();
    }

    @PostMapping("/newaudio")
    @CrossOrigin(origins = "http://127.0.0.1:5173/")
    public  String upload(@RequestParam("audio") MultipartFile file) throws IOException, GeneralSecurityException{
        return googleDriveService.
    }
}