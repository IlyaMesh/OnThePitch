package com.onthepitch.shared.model;

public class PhotoResponse {
    private String message;

    public PhotoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
