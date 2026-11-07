package com.matecomp.openai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service//para dcirle q es un componente inyectable de spring
public class AiServiceImpl implements AiServices{

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();;
    }

    @Override
    public String greeting() {
        return this.chatClient
                .prompt()
                .user("Dime Hola Mundo en ingles en una sola linea ")
                .call() //this is like send, is async
                .content();
    }
}
