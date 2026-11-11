package com.matecomp.openai.services;

import com.matecomp.openai.model.CityInfo;
import com.matecomp.openai.model.CodeDTO;
import com.matecomp.openai.model.Requirement;
import com.matecomp.openai.model.TicketClasification;

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



}
