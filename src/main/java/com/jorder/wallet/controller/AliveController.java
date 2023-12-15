package com.jorder.wallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/alive")
public class AliveController {
    
    @GetMapping("")
    public String alive() {
        return "Alive";
    }
    

}
