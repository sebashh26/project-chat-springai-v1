package com.matecomp.openai.services;

public interface AiService {

    String greeting(String name);

    String chat (String prompt);

    String chatExpertSpring(String prompt);

}
