package com.avir.ai.care.aiservice;

import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

public interface CommonStreamAiService {

    /**
     * 流式输出AI问答
     * @param userPrompt 用户提示词
     * @return 流式结果
     */
    @SystemMessage(fromResource = "prompt/common_prompt.txt")
    public Flux<String> commonMessage(String userPrompt);

}
