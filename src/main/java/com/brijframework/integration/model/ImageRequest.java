package com.brijframework.integration.model;

public class ImageRequest {
	private String model;
	private String prompt;
	private Integer n;
	private String size;

	public ImageRequest(String model, String prompt) {
		this.model="dall-e-3";
		this.prompt=prompt;
		this.size="1024x1024";
		this.n=1;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}