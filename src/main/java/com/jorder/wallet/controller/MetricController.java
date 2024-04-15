package com.jorder.wallet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metrics")
public class MetricController {
    
    @GetMapping("cost-in-month")
    public String costInMonth(){
        return "Under construction";
    }
    
}
