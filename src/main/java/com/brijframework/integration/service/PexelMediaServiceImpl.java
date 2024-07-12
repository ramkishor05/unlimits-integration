package com.brijframework.integration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.unlimits.rest.crud.beans.PageDetail;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;
import com.brijframework.integration.model.pixel.PixelPageDetail;

@Service
public class PexelMediaServiceImpl implements PexelMediaService {

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
		PixelPageDetail forObject = restTemplate.getForObject(urlimages, PixelPageDetail.class);
		forObject.getPhotos().forEach(photo -> {
			FileContent fileContent = new FileContent(photo.getId()+"", photo.getAlt(), "file");
			fileContent.setUrl(photo.getSrc().getOriginal());
			contents.add(fileContent);
		});
		return contents;
	}

	@Override
	public FileContent getFileContent(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PageDetail getAllFilesPage(String name, int pageNumber, int count) {
		PageDetail pageDetail=new PageDetail();
		PixelPageDetail forObject = restTemplate.getForObject(urlimages, PixelPageDetail.class);
		pageDetail.setPageCount(count);
		pageDetail.setTotalCount(forObject.getTotalResults());
		pageDetail.setTotalPages(forObject.getPage());
		pageDetail.setElements(forObject.getPhotos().stream().map(photo->{
			FileContent fileContent = new FileContent(photo.getId()+"", photo.getAlt(), "file");
			fileContent.setUrl(photo.getSrc().getOriginal());
			return fileContent;
		}).toList());
		return pageDetail;
	}
}
