package com.avir.ai.service;

import com.mybatisflex.core.service.IService;
import com.avir.ai.model.entity.User;

/**
 * 用户 服务层。
 *
 * @author fffe
 */
public interface UserService extends IService<User> {


    /**
     * 注册
     * @param userAccount 账号
     * @param password 密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    public long register(String userAccount, String password, String checkPassword);

}
