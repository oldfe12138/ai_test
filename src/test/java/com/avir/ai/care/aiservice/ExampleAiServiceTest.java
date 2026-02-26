package com.avir.ai.care.aiservice;

import com.avir.ai.care.aiservice.example.ExampleAiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class ExampleAiServiceTest {

    @Autowired
    private ExampleAiService exampleAiService;

    @Test
    public void ExampleMessageTest(){
        String s = exampleAiService.ExampleMessage("请介绍下中航红外");
        Assertions.assertNotNull(s);
    }
}
