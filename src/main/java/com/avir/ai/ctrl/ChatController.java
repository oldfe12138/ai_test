package com.avir.ai.ctrl;

import com.avir.ai.facade.CommonAiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final CommonAiFacade commonAiFacade;

    @Autowired
    public ChatController(CommonAiFacade commonAiFacade) {
        this.commonAiFacade = commonAiFacade;
    }

    @PostMapping("/common")
    public String sendMessage(@RequestBody String message) {
        return commonAiFacade.chat(message);
    }

}
