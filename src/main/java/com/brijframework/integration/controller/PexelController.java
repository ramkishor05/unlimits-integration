package com.brijframework.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;
import com.brijframework.integration.service.PexelDriveService;

@RestController()
@RequestMapping("/pexel/drive")
public class PexelController {

	@Autowired
	PexelDriveService pexelDriveService;

    @GetMapping("/folders/{fileId}")
    public  List<MediaContent> getAllFolders(@PathVariable String  fileId) throws Exception{
        return pexelDriveService.getAllFolders(fileId);
    }

    @GetMapping("/files/{fileId}")
    public  List<MediaContent> getAllFiles(@PathVariable String  fileId) throws Exception{
        return pexelDriveService.getAllFiles(fileId);
    }
    
    @GetMapping("/content/{fileId}")
    public  FileContent getFileContent(@PathVariable String  fileId) throws Exception{
        return pexelDriveService.getFileContent(fileId);
    }
}
