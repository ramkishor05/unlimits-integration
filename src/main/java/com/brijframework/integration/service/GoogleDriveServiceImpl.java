package com.brijframework.integration.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.brijframework.integration.model.google.DirContent;
import com.brijframework.integration.model.google.FileContent;
import com.brijframework.integration.model.google.MediaContent;
import com.brijframework.integration.utils.ContentUtils;
import com.google.api.client.googleapis.MethodOverride;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.CommonGoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.Get;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

/* class to demonstrate use of Drive files list API */
@Service
public class GoogleDriveServiceImpl implements GoogleDriveService{
	/**
	 * 
	 */
	private static final String FOLDER = "folder";
	/**
	 * Application name.
	 */
	private static final String APPLICATION_NAME = "Google Drive";
	/**
	 * Global instance of the JSON factory.
	 */
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	@Value("${google.api.key}")
    private String key;
	
	@Value("${google.api.driveId}")
    private String driveId;
	

	public Drive getInstance() throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		MethodOverride credentials = new MethodOverride();// getCredentials(HTTP_TRANSPORT);
		GoogleClientRequestInitializer googleClientRequestInitializer = CommonGoogleClientRequestInitializer.newBuilder().setKey(key).build();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credentials)
				.setApplicationName(APPLICATION_NAME).setGoogleClientRequestInitializer(googleClientRequestInitializer)
				.build();
		return service;
	}

	private List<MediaContent> getFileContentList(Drive service, File folder) throws IOException {
		System.out.println("Folder: " + folder.getName());
		List<MediaContent> result = new ArrayList<MediaContent>();
		Files.List request = service.files().list();
		do {
			try {
				request.setQ("'" + folder.getId() + "' in parents");
				request.setPageSize(1);
				FileList fileList = request.execute();
				List<File> files = fileList.getFiles();
				for (File file : files) {
					if (file.getMimeType().equalsIgnoreCase("application/vnd.google-apps.folder")) {
						DirContent fileContent = new DirContent(file.getId() ,file.getName(), FOLDER);
						result.add(fileContent);
						fileContent.setFiles(getFileContentList(service, file));
					} else {
						FileContent fileContent = new FileContent(file.getId() ,file.getName(), file.getMimeType());
						result.add(fileContent);
					}
				}
				request.setPageToken(fileList.getNextPageToken());
			} catch (IOException e) {
				request.setPageToken(null);
			}
		} while (request.getPageToken() != null && request.getPageToken().length() > 0);
		return result;
	}
	
	@SuppressWarnings("unused")
	private List<MediaContent> getSubContentList(Drive service, File folder) throws IOException {
		System.out.println("Folder: " + folder.getName());
		List<MediaContent> result = new ArrayList<MediaContent>();
		Files.List request = service.files().list();
		do {
			try {
				request.setQ("'" + folder.getId() + "' in parents");
				FileList fileList = request.execute();
				List<File> files = fileList.getFiles();
				for (File file : files) {
					if (file.getMimeType().equalsIgnoreCase("application/vnd.google-apps.folder")) {
						DirContent fileContent = new DirContent(file.getId() ,file.getName(), FOLDER);
						result.add(fileContent);
					} else {
						FileContent fileContent = new FileContent(file.getId() ,file.getName(), file.getMimeType());
						result.add(fileContent);
					}
				}
				request.setPageToken(fileList.getNextPageToken());
			} catch (IOException e) {
				request.setPageToken(null);
			}
		} while (request.getPageToken() != null && request.getPageToken().length() > 0);
		return result;
	}

	private FileContent getFileContent(Drive service, String fileId) throws IOException {
		Get get = service.files().get(fileId);
		File file = get.execute();
		FileContent fileContent = new FileContent(file.getId() ,file.getName(), file.getMimeType());
		HttpResponse executeMedia = get.executeMedia();
		try (InputStream inputStream = executeMedia.getContent()) {
			String content="data:"+file.getMimeType()+";base64,"+ContentUtils.parseAsString(inputStream);
			fileContent.setContent(content);
		}
		return fileContent;
	}
	
	private List<MediaContent> getFileDirList(Drive service, File folder) throws IOException {
		System.out.println("Folder: " + folder.getName());
		List<MediaContent> result = new ArrayList<MediaContent>();
		Files.List request = service.files().list();
		do {
			try {
				request.setQ("'" + folder.getId() + "' in parents and mimeType='application/vnd.google-apps.folder'");
				request.setPageSize(1);
				FileList fileList = request.execute();
				List<File> files = fileList.getFiles();
				for (File file : files) {
					DirContent fileContent = new DirContent(file.getId() ,file.getName(), FOLDER);
					result.add(fileContent);
					fileContent.setFiles(getFileDirList(service, file));
				}
				request.setPageToken(fileList.getNextPageToken());
			} catch (IOException e) {
				request.setPageToken(null);
			}
		} while (request.getPageToken() != null && request.getPageToken().length() > 0);
		return result;
	}

	@Override
	public List<MediaContent> getAllFiles(String  fileId) throws Exception {
		Drive service = getInstance();
		File folder = service.files().get(fileId).execute();
		return getFileContentList(service, folder);
	}
	
	@Override
	public List<MediaContent> getAllFolders(String  fileId) throws Exception {
		Drive service = getInstance();
		File folder = service.files().get(fileId).execute();
		return getFileDirList(service, folder);
	}
	
	@Override
	public FileContent getFileContent(String fileId) throws Exception {
		Drive service = getInstance();
		return getFileContent(service, fileId);
	}

}