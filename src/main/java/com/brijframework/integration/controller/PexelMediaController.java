package com.brijframework.integration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brijframework.integration.beans.PageDetail;
import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;
import com.brijframework.integration.service.PexelMediaService;

@RestController()
@RequestMapping("/api/pexel/media")
public class PexelMediaController {

	@Autowired
	PexelMediaService pexelMediaService;

    @GetMapping("/folders/{fileId}")
    public  List<MediaContent> getAllFolders(@PathVariable String  fileId) throws Exception{
        return pexelMediaService.getAllFolders(fileId);
    }
    
    @GetMapping("/files/{fileId}")
    public  List<MediaContent> getAllFiles(@PathVariable String  fileId) throws Exception{
        return pexelMediaService.getAllFiles(fileId);
    }
    
    @GetMapping("/content/{fileId}")
    public  FileContent getFileContent(@PathVariable String  fileId) throws Exception{
        return pexelMediaService.getFileContent(fileId);
    }
    
    @GetMapping("/files/page/data/{pageNumber}/count/{count}/search")
    public PageDetail getAllFilesPage(@RequestParam(required = false) String name,@PathVariable int pageNumber,
			@PathVariable int count) throws Exception{
        return pexelMediaService.getAllFilesPage(name,pageNumber, count );
    }
}
