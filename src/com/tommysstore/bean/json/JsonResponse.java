package com.tommysstore.bean.json;

import java.util.Map;

public class JsonResponse<T> {

	private String status;
	private T data;
	private String location;
	private Map<String, String> errorMessages;
	private String customMessage;
	
	public String getStatus() {
		
		return status;
	}

	public void setStatus(String status) {
		
		this.status = status;
	}

	public T getData() {
		
		return data;
	}
	
	public void setData(T data) {
		
		this.data = data;
	}

	public String getLocation() {
		
		return location;
	}

	public void setLocation(String location) {
		
		this.location = location;
	}

	public Map<String, String> getErrorMessages() {
		
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		
		this.errorMessages = errorMessages;
	}

	public String getCustomMessage() {
		
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		
		this.customMessage = customMessage;
	}
}
