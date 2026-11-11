package com.matecomp.openai.controller;

import com.matecomp.openai.model.CityInfo;
import com.matecomp.openai.model.CodeDTO;
import com.matecomp.openai.model.Requirement;
import com.matecomp.openai.model.TicketClasification;
import com.matecomp.openai.services.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiService aiServices;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "jose") String name) {
        return aiServices.greeting(name);
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        return aiServices.chat(prompt);
    }

    @PostMapping("/chat-expert")
    public String chatExpert(@RequestBody String prompt) {
        return aiServices.chatExpertSpring(prompt);
    }

    @PostMapping("/generate-code")
    public CodeDTO generateBody(@RequestBody Requirement  requirement) {
        return aiServices.generateCode(requirement);
    }

    @PostMapping("/explain-code")
    public String generateBody(@RequestBody String code) {
        return aiServices.explain(code);
    }

    @PostMapping("/chat-format")
    public String chatFormat(@RequestBody String prompt) {
        return aiServices.chatFormat(prompt);
    }

    @PostMapping("/analize")
    public  String analize(@RequestBody String text) {
        return aiServices.analyze(text);
    }

    @GetMapping("/city-info")
    public CityInfo cityInfo(@RequestParam String info) {
        return  aiServices.cityInfo(info);
    }

    @PostMapping("/classify-type")
    public TicketClasification ticketClasification(@RequestBody String text) {
        return aiServices.clasifyType(text);
    }
}
