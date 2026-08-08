package com.chatbot.gemini.service;

import com.chatbot.gemini.client.GeminiClient;
import com.chatbot.gemini.model.EmailResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final GeminiClient geminiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EmailService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public EmailResponse getReply(String message, String tone) {

        String prompt = """
                Generate an email based on the following information.

                Email content:
                %s

                Tone:
                %s

                Return ONLY valid JSON in exactly this format:
                {
                  "subject": "email subject here",
                  "body": "complete email body here"
                }

                Do not use markdown code blocks.
                Do not add any explanation outside the JSON.
                """.formatted(message, tone);

        String response = geminiClient.generateContent(prompt);

        try {

            String cleanedResponse = response
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            JsonNode jsonNode = objectMapper.readTree(cleanedResponse);

            String subject = jsonNode.get("subject").asText();
            String body = jsonNode.get("body").asText();

            return new EmailResponse(subject, body);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to parse Gemini response",
                    e
            );
        }
    }
}