package com.chatbot.gemini.controller;

import com.chatbot.gemini.model.ChatRequest;
import com.chatbot.gemini.model.ChatResponse;
import com.chatbot.gemini.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {

        String reply = chatService.getReply(request.getMessage());

        return new ChatResponse(reply);
    }
}