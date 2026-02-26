package com.avir.ai.ctrl;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthy")
public class HealthyController {

    @GetMapping
    public Boolean isHealthy(){
        return true;
    }
}
