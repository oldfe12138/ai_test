package com.avir.ai.facade;


import com.avir.ai.care.aiservice.CommonAiService;
import com.avir.ai.care.aiservice.CommonStreamAiService;
import com.avir.ai.exception.BusinessException;
import com.avir.ai.model.entity.User;
import com.avir.ai.model.entity.UserChat;
import com.avir.ai.model.enums.ChatHistoryMessageTypeEnum;
import com.avir.ai.model.enums.ErrorCode;
import com.avir.ai.service.ChatHistoryService;
import com.avir.ai.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class CommonAiFacade {

    private final CommonAiService commonAiService;

    private final CommonStreamAiService commonStreamAiService;

    private final ChatHistoryService chatHistoryService;

    private final UserChatService userChatService;

    @Autowired
    public CommonAiFacade(CommonAiService commonAiService, CommonStreamAiService commonStreamAiService, ChatHistoryService chatHistoryService, UserChatService userChatService) {
        this.commonAiService = commonAiService;
        this.commonStreamAiService = commonStreamAiService;
        this.chatHistoryService = chatHistoryService;
        this.userChatService = userChatService;
    }

    /**
     * 基础问答
     *
     * @param userPrompt 用户提示词
     * @return ai返回结果
     */
    public String chat(Long chatId, String userPrompt, User loginUser) {
        // 1. 校验用户是否具有权限访问该对话，以及对话id是否存在
        UserChat chat = userChatService.getById(chatId);
        if (chat == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "对话id不存在");
        }
        if (!chat.getUserId().equals(loginUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "无权限访问该对话");
        }
        // 2. 存储用户的提示词
        chatHistoryService.addChatMessage(chatId, userPrompt, ChatHistoryMessageTypeEnum.USER.getValue(), loginUser.getId());
        // 3. 存储大模型返回结果
        String content = commonAiService.commonMessage(userPrompt);
        chatHistoryService.addChatMessage(chatId, userPrompt, ChatHistoryMessageTypeEnum.AI.getValue(), loginUser.getId());
        // 4. 返回
        return content;
    }

    /**
     * 流式问答
     *
     * @param userPrompt 用户提示词
     * @return 流式ai返回结果
     */
    public Flux<String> streamChat(Long chatId, String userPrompt, User loginUser) {
        // 1. 校验用户是否具有权限访问该对话，以及对话id是否存在
        UserChat chat = userChatService.getById(chatId);
        if (chat == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "对话id不存在");
        }
        if (!chat.getUserId().equals(loginUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "无权限访问该对话");
        }
        // 2. 存储用户的提示词
        chatHistoryService.addChatMessage(chatId, userPrompt, ChatHistoryMessageTypeEnum.USER.getValue(), loginUser.getId());
        Flux<String> contentFlux = commonStreamAiService.commonMessage(userPrompt);
        StringBuilder aiResponseBuilder = new StringBuilder();
        return contentFlux.map(chunk -> {
            aiResponseBuilder.append(chunk);
            return chunk;
        }).doOnComplete(() -> {
            // 3. 完成存储AI返回结果
            String aiResponse = aiResponseBuilder.toString();
            chatHistoryService.addChatMessage(chatId, aiResponse, ChatHistoryMessageTypeEnum.AI.getValue(), loginUser.getId());
        }).doOnError(error -> {
            // 4. 若异常则回复失败并存储
            String errorMessage = "AI 回复失败：" + error.getMessage();
            chatHistoryService.addChatMessage(chatId, errorMessage, ChatHistoryMessageTypeEnum.AI.getValue(), loginUser.getId());
        });
    }

}
