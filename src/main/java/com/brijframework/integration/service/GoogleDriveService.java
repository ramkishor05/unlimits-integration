package com.brijframework.integration.service;

import java.util.List;

import com.brijframework.integration.model.drive.MediaContent;

public interface GoogleDriveService {

	List<MediaContent> getAllFiles() throws Exception;

	List<MediaContent> getFilesWithSubContentOnly() throws Exception;

	List<MediaContent> getFilesWithSubContentOnly(String fileId) throws Exception;

}
