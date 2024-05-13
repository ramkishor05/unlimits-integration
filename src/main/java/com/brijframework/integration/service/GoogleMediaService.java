/**
 * 
 */
package com.brijframework.integration.service;

import java.util.List;

import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;

/**
 *  @author omnie
 */
public interface GoogleMediaService {

	/**
	 * @param fileId
	 * @return
	 */
	List<MediaContent> getAllFolders(String fileId);

	/**
	 * @param fileId
	 * @return
	 */
	List<MediaContent> getAllFiles(String fileId);

	/**
	 * @param fileId
	 * @return
	 * @throws Exception 
	 */
	FileContent getFileContent(String fileId) throws Exception;

}
