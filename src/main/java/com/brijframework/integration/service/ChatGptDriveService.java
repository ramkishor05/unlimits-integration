package com.brijframework.integration.service;

import com.brijframework.integration.model.ChatResponse;
import com.brijframework.integration.model.Model;

public interface ChatGptDriveService {

	ChatResponse chat(String prompt);

	Model model();

	String audio();

	String imagesGenerations(String prompt);

	String imagesEdit(String prompt);

}
