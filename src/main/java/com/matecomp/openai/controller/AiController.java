package com.matecomp.openai.controller;

import com.matecomp.openai.services.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiServices aiServices;

    @GetMapping("/greeting")
    public String greeting() {
        return aiServices.greeting();
    }
}
