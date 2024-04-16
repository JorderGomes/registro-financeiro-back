package com.jorder.wallet.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.wallet.model.dto.MonthBalanceDto;
import com.jorder.wallet.repository.RegisterRepository;

@Service
public class CalcMonthBalance {

    @Autowired
    private RegisterRepository registerRepository;

    public MonthBalanceDto execute(String month) {

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

}
