package com.brijframework.integration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;
import com.brijframework.integration.model.pixel.Root;

@Service
public class PexelDriveServiceImpl implements PexelDriveService {

	@Qualifier("pexelsRestTemplate")
	@Autowired
	private RestTemplate restTemplate;

	@Value("${pexels.api.urlimages}")
	private String urlimages;

	@Override
	public List<MediaContent> getAllFolders(String fileId) {
		return null;
	}

	@Override
	public List<MediaContent> getAllFiles(String fileId) {
		List<MediaContent>  contents=new ArrayList<MediaContent>();
		Root forObject = restTemplate.getForObject(urlimages, Root.class);
		forObject.getPhotos().forEach(photo -> {
			contents.add(new FileContent(photo.getId()+"", photo.getUrl(), photo.getAlt()));
		});
		return contents;
	}

	@Override
	public FileContent getFileContent(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}
}
