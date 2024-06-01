package com.jorder.wallet.useCase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.wallet.model.dto.MonthBalanceDto;
import com.jorder.wallet.repository.TransactionRepository;

@Service
public class MetricsService {

    @Autowired
    private TransactionRepository registerRepository;

    public MonthBalanceDto calcMonthBalance(String month) {

        float general = registerRepository.getMonthlyGeneralBalance(month);
        float spent = registerRepository.getMonthlySpentBalance(month);
        float income = registerRepository.getMonthlyIncomeBalance(month);

        return MonthBalanceDto.builder()
        .month(month)
        .general(general)
        .spent(spent)
        .income(income)
        .build();
    }

    public List<Map<String, Float>> calcCostsByTag(){
        return registerRepository.getCostsByTag();
    }

}
