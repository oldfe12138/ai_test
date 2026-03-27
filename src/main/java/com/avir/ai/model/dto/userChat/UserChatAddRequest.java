package com.avir.ai.model.dto.userChat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 应用创建请求
 */
@Data
public class UserChatAddRequest implements Serializable {

    /**
     * 应用初始化的 prompt
     */
    @NotBlank(message = "用户提示词不得为空")
    private String initPrompt;

    private static final long serialVersionUID = 1L;
} 