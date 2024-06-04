package com.jorder.wallet.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.wallet.model.Transaction;
import com.jorder.wallet.model.dto.MonthBalanceDto;
import com.jorder.wallet.model.dto.PercentualIncomeCostsDto;
import com.jorder.wallet.useCase.MetricsService;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    @Autowired
    MetricsService metricsService;

    @GetMapping("/month-balance")
    public ResponseEntity<MonthBalanceDto> costInMonth(@RequestParam String month) {
        if (month.equals("")) {
            LocalDate todayDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
            month = todayDate.format(formatter);
        }
        MonthBalanceDto monthBalanceDto = metricsService.calcMonthBalance(month);
        return ResponseEntity.ok(monthBalanceDto);
    }

    @GetMapping("/expensive-costs")
    public ResponseEntity<List<Transaction>> getExpensiveCosts(@RequestParam String year_month) {
        return ResponseEntity.ok(this.metricsService.getCostsExpensives(year_month));
    }
    
    @GetMapping("/percentual-income-costs")
    public ResponseEntity<PercentualIncomeCostsDto> getPercentualIncomeCosts(@RequestParam String year_month) {
        return ResponseEntity.ok(this.metricsService.getPercentualIncomeCosts(year_month));
    }
    

    @GetMapping("/costs-by-tag")
    public ResponseEntity<List<Map<String, Float>>> costsByTag() {
        return ResponseEntity.ok(metricsService.calcCostsByTag());
    }

    @GetMapping("/costs-by-month")
    public ResponseEntity<List<Map<String, Float>>> costsByMonth() {
        return ResponseEntity.ok(metricsService.getCostsByMonth());
    }
    

}
