package com.matecomp.openai.services;

import com.matecomp.openai.model.*;
import org.springframework.cglib.core.CodeGenerationException;

public interface AiService {

    String greeting(String name);

    String chat (String prompt);

    String chatExpertSpring(String prompt);

    CodeDTO generateCode  (Requirement request);

    String explain(String code);

    String chatFormat(String topic);

    String analyze(String text);

    CityInfo cityInfo(String info);

    TicketClasification clasifyType(String text);

    CodeGeneration generateCodeType(String requirement);



}
