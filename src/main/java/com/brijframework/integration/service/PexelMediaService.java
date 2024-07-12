package com.brijframework.integration.service;

import java.util.List;

import org.unlimits.rest.crud.beans.PageDetail;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;

public interface PexelMediaService {

	List<MediaContent> getAllFolders(String fileId);

	List<MediaContent> getAllFiles(String fileId);

	FileContent getFileContent(String fileId);

	PageDetail getAllFilesPage(String name, int pageNumber, int count);

}
