package com.matecomp.openai.controller;

import com.matecomp.openai.services.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiService aiServices;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(defaultValue = "jose") String name) {
        return aiServices.greeting(name);
    }
}
