package com.brijframework.integration.model;

import java.util.List;

public class ChatResponse {

	private List<Choice> choices;
	
	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

}