package com.brijframework.integration.service;

import com.brijframework.integration.model.ChatResponse;

public interface ChatService {

	ChatResponse chat(String prompt);

}
