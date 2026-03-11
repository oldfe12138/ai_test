package com.avir.ai.service;

import com.avir.ai.model.entity.ChatHistory;
import com.mybatisflex.core.service.IService;

/**
 * 对话历史 服务层。
 *
 * @author fffe
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    boolean addChatMessage(Long appId, String message, String messageType, Long userId);
}
