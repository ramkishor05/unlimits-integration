package com.brijframework.integration.service;

import java.util.List;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;

public interface GoogleDriveService {

	List<MediaContent> getAllFiles(String fileId) throws Exception;

	List<MediaContent> getAllFolders(String fileId) throws Exception;

	FileContent getFileContent(String fileId) throws Exception;

}
