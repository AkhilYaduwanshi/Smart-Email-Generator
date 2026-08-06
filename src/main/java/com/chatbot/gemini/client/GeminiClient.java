package com.chatbot.gemini.client;

import com.chatbot.gemini.exception.GeminiApiException;
import com.chatbot.gemini.model.dto.Content;
import com.chatbot.gemini.model.dto.GeminiRequest;
import com.chatbot.gemini.model.dto.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.chatbot.gemini.model.dto.GeminiResponse;

import java.util.List;

@Component
public class GeminiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public GeminiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String generateContent(String message) {

        try {

            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=" + apiKey;

            // Request DTO
            Part part = new Part(message);
            Content content = new Content(List.of(part));
            GeminiRequest body = new GeminiRequest(List.of(content));

            // API Call
            GeminiResponse response = webClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(body))
                    .retrieve()
                    .bodyToMono(GeminiResponse.class)
                    .block();

            // Response Parsing
            return response.getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();

        } catch (Exception e) {
            throw new GeminiApiException( "Unable to connect to Gemini API",e);
        }
    }
}