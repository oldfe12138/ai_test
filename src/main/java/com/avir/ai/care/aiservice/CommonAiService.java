package com.avir.ai.care.aiservice;

import dev.langchain4j.service.SystemMessage;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface CommonAiService {

    /**
     * 通用问答service，提供最基础问答功能
     * @param userPrompt 用户提示词
     * @return 问答结果
     */
    @SystemMessage(fromResource = "prompt/common_prompt.txt")
    public String commonMessage(String userPrompt);

}
