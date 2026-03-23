package com.avir.ai.ctrl;

import com.avir.ai.facade.CommonAiFacade;
import com.avir.ai.model.entity.User;
import com.avir.ai.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Validated
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final CommonAiFacade commonAiFacade;

    private final UserService userService;

    @Autowired
    public ChatController(CommonAiFacade commonAiFacade, UserService userService) {
        this.commonAiFacade = commonAiFacade;
        this.userService = userService;
    }

    /**
     * 普通问答
     * @param userPrompt 用户提示词
     * @return ai返回结果
     */
    @PostMapping("/common")
    public String chat( @RequestBody @NotNull(message = "对话ID不得为空") Long chatId,
                        @RequestBody @NotBlank(message = "提示词不得为空") String userPrompt,
                        HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return commonAiFacade.chat(chatId, userPrompt, loginUser);
    }

    /**
     * 流式问答
     * @param userPrompt 用户提示词
     * @return ai返回结果
     */
    @PostMapping("/streamCommon")
    public Flux<String> streamChat(@RequestBody @NotNull(message = "对话ID不得为空") Long chatId,
                                   @RequestBody @NotBlank(message = "提示词不得为空") String userPrompt,
                                   HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        return commonAiFacade.streamChat(chatId, userPrompt, loginUser);
    }

}
