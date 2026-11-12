package com.matecomp.openai.model;

import java.util.List;

public record CodeGeneration(String requirement, String summary, String code, List<String> notes) {
}
