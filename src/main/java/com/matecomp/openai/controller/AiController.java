package com.matecomp.openai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    private final ChatClient chatClient;

    public  AiController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/greeting")
    public String greeting() {
        return this.chatClient
                .prompt()
                .user("Dime Hola Mundo en ingles en una sola linea")
                .call() //this is like send, is async
                .content();
    }
}
