package com.avir.ai.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.avir.ai.model.entity.UserChat;
import com.avir.ai.mapper.UserChatMapper;
import com.avir.ai.service.UserChatService;
import org.springframework.stereotype.Service;

/**
 * 对话主表 服务层实现。
 *
 * @author fffe
 */
@Service
public class UserUserChatServiceImpl extends ServiceImpl<UserChatMapper, UserChat>  implements UserChatService {

}
