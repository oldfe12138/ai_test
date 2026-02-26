package com.avir.ai.facade;


import com.avir.ai.care.aiservice.CommonAiService;
import com.avir.ai.care.aiservice.CommonStreamAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class CommonAiFacade {

    private final CommonAiService commonAiService;

    private final CommonStreamAiService commonStreamAiService;

    @Autowired
    public CommonAiFacade(CommonAiService commonAiService, CommonStreamAiService commonStreamAiService) {
        this.commonAiService = commonAiService;
        this.commonStreamAiService = commonStreamAiService;
    }

    /**
     * 基础问答
     * @param userPrompt 用户提示词
     * @return ai返回结果
     */
    public String chat(String userPrompt){
        return commonAiService.commonMessage(userPrompt);
    }

    /**
     * 流式问答
     * @param userPrompt 用户提示词
     * @return 流式ai返回结果
     */
    public Flux<String> streamChat(String userPrompt){
        return commonStreamAiService.commonMessage(userPrompt);
    }

}
