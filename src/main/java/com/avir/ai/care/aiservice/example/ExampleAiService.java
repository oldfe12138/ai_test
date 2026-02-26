package com.avir.ai.care.aiservice.example;


import dev.langchain4j.service.SystemMessage;

public interface ExampleAiService {

    /**
     * 测试注解开发AIService
     *
     * @param userMessage 用户提示词
     * @return AI调用返回结果
     */
    @SystemMessage(value = "prompt/example_prompt.txt")
    public String ExampleMessage(String userMessage);

}
