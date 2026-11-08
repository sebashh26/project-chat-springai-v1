package com.matecomp.openai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service//para dcirle q es un componente inyectable de spring
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();;
    }

    @Override
    public String greeting(String name) {
        return this.chatClient
                .prompt()
                .system("Response siempre en ingles y en una sola linea ")
                .user("Dime Hola Mundo con mi nombre: %s".formatted(name))
                .call() //this is like send, is async
                .content();
    }

    @Override
    public String chat(String prompt) {
        return this.chatClient
                .prompt()
                .user(prompt)
                .call() //this is like send, is async
                .content();
    }
}
