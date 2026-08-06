package com.chatbot.gemini.client;

import com.chatbot.gemini.model.dto.Content;
import com.chatbot.gemini.model.dto.GeminiRequest;
import com.chatbot.gemini.model.dto.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class GeminiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public GeminiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String generateContent(String message) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=" + apiKey;

        // Request DTO
        Part part = new Part(message);
        Content content = new Content(List.of(part));
        GeminiRequest body = new GeminiRequest(List.of(content));

        // API Call
        Map<String, Object> response = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Response Parsing
        List<Map<String, Object>> candidates =
                (List<Map<String, Object>>) response.get("candidates");

        Map<String, Object> contentMap =
                (Map<String, Object>) candidates.get(0).get("content");

        List<Map<String, Object>> parts =
                (List<Map<String, Object>>) contentMap.get("parts");

        return parts.get(0).get("text").toString();
    }
}