package com.brijframework.integration.service;

import com.brijframework.integration.model.Model;

public interface ChatGptMediaService {

	String text(String prompt);

	Model model();

	String audio();

	String imagesGenerations(String prompt);

	String imagesEdit(String prompt);

}
