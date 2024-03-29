package com.nagarro.miniAssignment2.exception;

public class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private final String message;
    private final int code;
    private final String timestamp;

    public InvalidInputException(String message, int code, String timestamp) {
        
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

   
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
