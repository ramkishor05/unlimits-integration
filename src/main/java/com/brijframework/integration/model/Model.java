package com.brijframework.integration.model;

import java.util.List;

public class Model {
	
	private String object;
	private List<ModelObject> data;

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<ModelObject> getData() {
		return data;
	}

	public void setData(List<ModelObject> data) {
		this.data = data;
	}
}