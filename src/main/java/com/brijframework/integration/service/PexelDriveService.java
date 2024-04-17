package com.brijframework.integration.service;

import java.util.List;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;

public interface PexelDriveService {

	List<MediaContent> getAllFolders(String fileId);

	List<MediaContent> getAllFiles(String fileId);

	FileContent getFileContent(String fileId);

}
