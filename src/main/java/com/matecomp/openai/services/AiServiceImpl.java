package com.matecomp.openai.services;

import com.matecomp.openai.model.CodeDTO;
import com.matecomp.openai.model.Requirement;
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

    @Override
    public String chatExpertSpring(String prompt) {
        return this.chatClient
                .prompt()
                .system("Eres un experto en Java y Spring Boot. Responde de forma clara y simple, la pregunta debe estar realcionado si o si a spring boot caso contrario  responde que no sabes ")
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public CodeDTO generateCode(Requirement request) {
        String code= chatClient
                .prompt()
                .system("Eres un dasarrolador senior experto en java con spring boot con buenas practicas, una pregunta debe ester relacionada si y solo si  a java con spring boot  otra no relacionada diles que no sabes")
                .user(request.requirement())
                .call()
                .content();

        return new CodeDTO(code);
    }
}
