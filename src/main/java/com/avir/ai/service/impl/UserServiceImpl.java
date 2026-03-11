package com.avir.ai.service.impl;

import cn.hutool.core.util.StrUtil;
import com.avir.ai.model.enums.UserRoleEnum;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.avir.ai.model.entity.User;
import com.avir.ai.mapper.UserMapper;
import com.avir.ai.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 用户 服务层实现。
 *
 * @author fffe
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService{


    @Override
    public long register(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验参数
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new RuntimeException("参数为空");
        }
        if (userAccount.length() < 4) {
            throw new RuntimeException("账号长度过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new RuntimeException("密码长度过短");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new RuntimeException("两次输入的密码不一致");
        }
        // 2. 查询用户是否已存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.mapper.selectCountByQuery(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("账号重复");
        }
        // 3. 加密密码
        String encryptPassword = getEncryptPassword(userPassword);
        // 4. 创建用户，插入数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("oldfe12138");
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new RuntimeException("注册失败，数据库错误");
        }
        return user.getId();
    }

    private String getEncryptPassword(String userPassword) {
        // 盐值，混淆密码
        final String SALT = "oldfe12138";
        return DigestUtils.md5DigestAsHex((userPassword + SALT).getBytes(StandardCharsets.UTF_8));
    }

}
