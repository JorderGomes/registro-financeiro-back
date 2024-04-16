package com.jorder.wallet.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.wallet.model.dto.MonthBalanceDto;
import com.jorder.wallet.useCase.CalcMonthBalance;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    @Autowired
    CalcMonthBalance calcMonthBalance;

    @GetMapping("/month-balance")
    public ResponseEntity<MonthBalanceDto> costInMonth(@RequestParam String month) {
        if (month.equals("")) {
            LocalDate todayDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
            month = todayDate.format(formatter);
        }
        MonthBalanceDto monthBalanceDto = calcMonthBalance.execute(month);
        return ResponseEntity.ok(monthBalanceDto);
    }

}
