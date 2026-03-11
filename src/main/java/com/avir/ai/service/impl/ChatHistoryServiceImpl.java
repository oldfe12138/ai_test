package com.avir.ai.service.impl;

import com.avir.ai.model.entity.ChatHistory;
import com.avir.ai.mapper.ChatHistoryMapper;
import com.avir.ai.service.ChatHistoryService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 对话历史 服务层实现。
 *
 * @author fffe
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory>  implements ChatHistoryService{

    @Override
    public boolean addChatMessage(Long appId, String message, String messageType, Long userId) {
        // 插入数据库
        ChatHistory chatHistory = ChatHistory.builder()
                .appId(appId)
                .message(message)
                .messageType(messageType)
                .userId(userId)
                .build();
        return this.save(chatHistory);
    }

}
