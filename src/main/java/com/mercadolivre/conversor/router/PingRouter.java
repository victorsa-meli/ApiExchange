package com.mercadolivre.conversor.router;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingRouter {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
