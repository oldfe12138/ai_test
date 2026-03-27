package com.avir.ai.service.impl;

import com.avir.ai.model.dto.chathistory.ChatHistoryQueryRequest;
import com.avir.ai.model.entity.ChatHistory;
import com.avir.ai.mapper.ChatHistoryMapper;
import com.avir.ai.model.entity.User;
import com.avir.ai.service.ChatHistoryService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层实现。
 *
 * @author fffe
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory>  implements ChatHistoryService{

    @Override
    public boolean addChatMessage(Long userChatId, String message, String messageType, Long userId) {
        // 插入数据库
        ChatHistory chatHistory = ChatHistory.builder()
                .userChatId(userChatId)
                .message(message)
                .messageType(messageType)
                .userId(userId)
                .build();
        return this.save(chatHistory);
    }

    @Override
    public boolean deleteByUserChatId(Long userChatId) {
        return false;
    }

    @Override
    public Page<ChatHistory> listUserChatHistoryByPage(Long userChatId, int pageSize, LocalDateTime lastCreateTime, User loginUser) {
        return null;
    }

    @Override
    public int loadChatHistoryToMemory(Long userChatId, MessageWindowChatMemory chatMemory, int maxCount) {
        return 0;
    }

    @Override
    public QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest) {
        return null;
    }

}
