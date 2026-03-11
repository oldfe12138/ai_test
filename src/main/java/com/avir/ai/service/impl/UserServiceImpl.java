package com.avir.ai.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.avir.ai.entity.User;
import com.avir.ai.mapper.UserMapper;
import com.avir.ai.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现。
 *
 * @author fffe
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{

}
