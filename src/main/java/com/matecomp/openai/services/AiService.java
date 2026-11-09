package com.matecomp.openai.services;

import com.matecomp.openai.model.CodeDTO;
import com.matecomp.openai.model.Requirement;

public interface AiService {

    String greeting(String name);

    String chat (String prompt);

    String chatExpertSpring(String prompt);

    CodeDTO generateCode  (Requirement request);



}
