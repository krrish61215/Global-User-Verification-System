package com.nagarro.miniAssignment2.model;

public class CustomErrorResponse {
    private String message;
    private int code;
    private String timestamp;

   
    public CustomErrorResponse(String message, int code, String timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

    
}
