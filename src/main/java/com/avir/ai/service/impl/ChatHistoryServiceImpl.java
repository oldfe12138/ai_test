package com.avir.ai.service.impl;

import com.avir.ai.entity.ChatHistory;
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

}
