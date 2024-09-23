package com.brijframework.integration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brijframework.integration.beans.PageDetail;
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
	public List<MediaContent> getAllFiles(String query) {
		String url=urlimages+"?query="+query+"&per_page="+100;
		List<MediaContent>  contents=new ArrayList<MediaContent>();
		PixelPageDetail forObject = restTemplate.getForObject(url, PixelPageDetail.class);
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
	public PageDetail getAllFilesPage(String query, int pageNumber, int count) {
		PageDetail pageDetail=new PageDetail();
		String url=urlimages+"?query="+query+"&per_page="+count;
		PixelPageDetail forObject = restTemplate.getForObject(url, PixelPageDetail.class);
		if(forObject!=null) {
			pageDetail.setPageCount(count);
			pageDetail.setTotalCount(forObject.getPhotos().size());
			pageDetail.setTotalPages(forObject.getPage());
			pageDetail.setElements(forObject.getPhotos().stream().map(photo->{
				FileContent fileContent = new FileContent(photo.getId()+"", photo.getAlt(), "file");
				fileContent.setUrl(photo.getSrc().getOriginal());
				return fileContent;
			}).toList());
		}
		return pageDetail;
	}
}
