package com.avir.ai.ctrl;

import com.avir.ai.common.BaseResponse;
import com.avir.ai.common.ResultUtils;
import com.avir.ai.model.dto.userChat.UserChatAddRequest;
import com.avir.ai.model.entity.User;
import com.avir.ai.model.entity.UserChat;
import com.avir.ai.service.UserChatService;
import com.avir.ai.service.UserService;
import com.mybatisflex.core.paginate.Page;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对话主表 控制层。
 *
 * @author fffe
 */
@Validated
@RestController
@RequestMapping("/userChat")
public class UserChatController {

    private final UserChatService userChatService;

    private final UserService userService;

    @Autowired
    public UserChatController(UserChatService userChatService, UserService userService) {
        this.userChatService = userChatService;
        this.userService = userService;
    }

    /**
     * 创建应用
     *
     * @param addRequest 用户提示词
     * @param request    请求
     * @return 应用 id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addUserChat(@RequestBody UserChatAddRequest addRequest, HttpServletRequest request) {
        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        // 构造用户对话对象
        UserChat userChat = UserChat.builder()
                .chatName(addRequest.getInitPrompt().substring(0, Math.min(10, addRequest.getInitPrompt().length())))
                .userId(loginUser.getId())
                .initPrompt(addRequest.getInitPrompt())
                .priority(0)
                .build();
        // 插入数据库
        userChatService.save(userChat);

        return ResultUtils.success(userChat.getId());
    }


    /**
     * 保存对话主表。
     *
     * @param userChat 对话主表
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody UserChat userChat) {
        return userChatService.save(userChat);
    }

    /**
     * 根据主键删除对话主表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return userChatService.removeById(id);
    }

    /**
     * 根据主键更新对话主表。
     *
     * @param userChat 对话主表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody UserChat userChat) {
        return userChatService.updateById(userChat);
    }

    /**
     * 查询所有对话主表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<UserChat> list() {
        return userChatService.list();
    }

    /**
     * 根据主键获取对话主表。
     *
     * @param id 对话主表主键
     * @return 对话主表详情
     */
    @GetMapping("getInfo/{id}")
    public UserChat getInfo(@PathVariable Long id) {
        return userChatService.getById(id);
    }

    /**
     * 分页查询对话主表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<UserChat> page(Page<UserChat> page) {
        return userChatService.page(page);
    }

}
