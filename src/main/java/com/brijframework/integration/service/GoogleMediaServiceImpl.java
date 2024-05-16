/**
 * 
 */
package com.brijframework.integration.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brijframework.integration.beans.UIGlobalMediaItem;
import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;

/**
 * @author omnie
 */
@Service
public class GoogleMediaServiceImpl implements GoogleMediaService {

	private static final String FOLDER = "folder";

	@Autowired
	private GoogleDriveService googleDriveService;

	@Autowired
	@Qualifier("allRestTemplate")
	private RestTemplate restTemplate;
	
	@Value("${content.api.url}")
	private String contentApiUrl;

	@Override
	public List<MediaContent> getAllFolders(String fileId) {
		try {
			List<MediaContent> mediaContents = googleDriveService.getAllFolders(fileId);
			mediaContents.forEach(mediaContent -> {
				if (FOLDER.equals(mediaContent.getType())) {
					getAllFolders(mediaContent.getId());
				} else {
					FileContent fileContent = getFileContent(mediaContent.getId());
					BeanUtils.copyProperties(fileContent, mediaContent);
				}
			});
			return mediaContents;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MediaContent> getAllFiles(String fileId) {
		try {
			List<MediaContent> mediaContents = googleDriveService.getAllFiles(fileId);
			mediaContents.forEach(mediaContent -> {
				try {
					getFileContent(mediaContent.getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			return mediaContents;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FileContent getFileContent(String fileId) {
		try {
			FileContent fileContent = googleDriveService.getFileContent(fileId);
			UIGlobalMediaItem mediaItem = new UIGlobalMediaItem();
			mediaItem.setIdenNo(fileContent.getId());
			mediaItem.setName(fileContent.getName());
			mediaItem.setTypeId(fileContent.getType());
			mediaItem.setContent(fileContent.getContent());
			restTemplate.put(contentApiUrl+"/api/global/media/item", mediaItem, UIGlobalMediaItem.class);
			return fileContent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
