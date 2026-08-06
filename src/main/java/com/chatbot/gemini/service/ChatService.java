package com.chatbot.gemini.service;

import com.chatbot.gemini.client.GeminiClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final GeminiClient geminiClient;

    public ChatService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String getReply(String message) {
        return geminiClient.generateContent(message);
    }
}