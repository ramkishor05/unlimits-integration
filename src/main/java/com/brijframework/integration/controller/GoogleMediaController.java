package com.brijframework.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;
import com.brijframework.integration.service.GoogleMediaService;

@RestController()
@RequestMapping("/api/google/media")
public class GoogleMediaController {

    @Autowired
    GoogleMediaService googleMediaService;

    @PostMapping("/folders/{fileId}")
    public  List<MediaContent> getAllFolders(@PathVariable String  fileId) throws Exception{
        return googleMediaService.getAllFolders(fileId);
    }

    @PostMapping("/files/{fileId}")
    public  List<MediaContent> getAllFiles(@PathVariable String  fileId) throws Exception{
        return googleMediaService.getAllFiles(fileId);
    }
    
    @PostMapping("/content/{fileId}")
    public  FileContent getFileContent(@PathVariable String  fileId) throws Exception{
        return googleMediaService.getFileContent(fileId);
    }
    
}