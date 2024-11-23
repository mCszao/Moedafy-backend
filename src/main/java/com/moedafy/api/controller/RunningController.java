package com.moedafy.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningController {

    @GetMapping("/running")
    public String isRunning(){
        return "Servidor Moedafy está rodando corretamente...";
    }

}