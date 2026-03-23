package com.avir.ai.ctrl;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.avir.ai.model.entity.UserChat;
import com.avir.ai.service.UserChatService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 对话主表 控制层。
 *
 * @author fffe
 */
@RestController
@RequestMapping("/userChat")
public class UserChatController {

    @Autowired
    private UserChatService userChatService;

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
