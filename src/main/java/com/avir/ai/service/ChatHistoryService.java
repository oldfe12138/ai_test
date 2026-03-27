package com.avir.ai.service;

import com.avir.ai.model.dto.chathistory.ChatHistoryQueryRequest;
import com.avir.ai.model.entity.ChatHistory;
import com.avir.ai.model.entity.User;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 * @author fffe
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    boolean addChatMessage(Long userChatId, String message, String messageType, Long userId);

    /**
     * 根据应用 id 删除对话历史
     *
     * @param userChatId
     * @return
     */
    boolean deleteByUserChatId(Long userChatId);

    /**
     * 分页查询某 APP 的对话记录
     *
     * @param userChatId
     * @param pageSize
     * @param lastCreateTime
     * @param loginUser
     * @return
     */
    Page<ChatHistory> listUserChatHistoryByPage(Long userChatId, int pageSize,
                                                LocalDateTime lastCreateTime,
                                                User loginUser);

    /**
     * 加载对话历史到内存
     *
     * @param userChatId
     * @param chatMemory
     * @param maxCount 最多加载多少条
     * @return 加载成功的条数
     */
    int loadChatHistoryToMemory(Long userChatId, MessageWindowChatMemory chatMemory, int maxCount);

    /**
     * 构造查询条件
     *
     * @param chatHistoryQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);
}
