package com.chatbot.gemini.model;

import jakarta.validation.constraints.NotBlank;

public class ChatRequest {

    @NotBlank(message = "Message must not be blank")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}