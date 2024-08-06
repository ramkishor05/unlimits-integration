package com.brijframework.integration.service;

import org.brijframework.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brijframework.integration.model.ChatResponse;
import com.brijframework.integration.model.Choice;
import com.brijframework.integration.model.Message;
import com.brijframework.integration.model.Model;

@Service
public class ChatGptMediaServiceImpl implements ChatGptMediaService {

	@Autowired
	private ChatGptDriveService chatGptDriveService;

	@Override
	public String text(String prompt) {
		try {
			ChatResponse chatResponse = chatGptDriveService.chat(prompt);
			for (Choice choice : chatResponse.getChoices()) {
				Message message = choice.getMessage();
				if (StringUtil.isNonEmpty(message.getContent())) {
					return message.getContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong in cpt";
		}
		return "";
	}

	@Override
	public Model model() {
		return chatGptDriveService.model();
	}

	@Override
	public String audio() {
		return chatGptDriveService.audio();
	}

	@Override
	public String imagesGenerations(String prompt) {
		return chatGptDriveService.imagesGenerations(prompt);
	}

	@Override
	public String imagesEdit(String prompt) {
		return chatGptDriveService.imagesEdit(prompt);
	}
}
