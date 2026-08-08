package com.chatbot.gemini.controller;

import com.chatbot.gemini.model.EmailRequest;
import com.chatbot.gemini.model.EmailResponse;
import com.chatbot.gemini.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/generate-email")
public class EmailController {

    private final EmailService chatService;

    public EmailController(EmailService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public EmailResponse chat(@Valid @RequestBody EmailRequest request) {

        return chatService.getReply(
                request.getMessage(),
                request.getTone()
        );
    }
}