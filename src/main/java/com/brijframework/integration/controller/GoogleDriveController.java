package com.brijframework.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.drive.MediaContent;
import com.brijframework.integration.service.GoogleDriveService;

@RestController()
@RequestMapping("/google/drive")
public class GoogleDriveController {

    @Autowired
    GoogleDriveService googleDriveService;


    @GetMapping("/with/all/files")
    public  List<MediaContent> getAllFiles() throws Exception{
        return googleDriveService.getAllFiles();
    }
    
    @GetMapping("/with/sub/files")
    public  List<MediaContent> getSubFiles() throws Exception{
        return googleDriveService.getFilesWithSubContentOnly();
    }
    
    @GetMapping("/with/sub/files/{fileId}")
    public  List<MediaContent> getSubFiles(@PathVariable String fileId) throws Exception{
        return googleDriveService.getFilesWithSubContentOnly(fileId);
    }
}