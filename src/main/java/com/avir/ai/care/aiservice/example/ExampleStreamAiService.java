package com.avir.ai.care.aiservice.example;


import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface ExampleStreamAiService {

    /**
     * 测试注解开发AIService
     *
     * @param userMessage 用户提示词
     * @return AI调用返回结果
     */
    @SystemMessage(fromResource = "prompt/example_prompt.txt")
    public Flux<String> ExampleMessage(String userMessage);

}
