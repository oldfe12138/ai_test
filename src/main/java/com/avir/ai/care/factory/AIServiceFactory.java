package com.avir.ai.care.factory;


import com.avir.ai.care.aiservice.CommonAiService;
import com.avir.ai.care.aiservice.CommonStreamAiService;
import com.avir.ai.care.aiservice.example.ExampleAiService;
import com.avir.ai.care.aiservice.example.ExampleStreamAiService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AIService 创建工厂
 */
@Configuration
public class AIServiceFactory {

    private final ChatModel chatModel;

    private final StreamingChatModel streamingChatModel;

    @Autowired
    public AIServiceFactory(ChatModel chatModel, StreamingChatModel streamingChatModel) {
        this.chatModel = chatModel;
        this.streamingChatModel = streamingChatModel;
    }

    /**
     * 创建标准 aiService
     *
     * @return aiService
     */
    @Bean
    public CommonAiService commonAiService() {
        return AiServices.builder(CommonAiService.class)
                .chatModel(chatModel)
                .build();
    }

    /**
     * 创建标准流式输出AI Service
     *
     * @return StreamAiService
     */
    @Bean
    public CommonStreamAiService commonStreamAiService() {
        return AiServices.builder(CommonStreamAiService.class)
                .streamingChatModel(streamingChatModel)
                .build();
    }

    /**
     * 创建AiService
     *
     * @return AiService
     */
    @Bean
    public ExampleAiService exampleAiService() {
        return AiServices.builder(ExampleAiService.class)
                .chatModel(chatModel)
                .build();
    }

    /**
     * 创建流式输出AiService
     *
     * @return AiService
     */
    @Bean
    public ExampleStreamAiService exampleStreamAiService() {
        return AiServices.create(ExampleStreamAiService.class, streamingChatModel);
    }
}
