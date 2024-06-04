package com.jorder.wallet.useCase;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.wallet.model.Transaction;
import com.jorder.wallet.model.dto.MonthBalanceDto;
import com.jorder.wallet.model.dto.PercentualIncomeCostsDto;
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

    public List<Transaction> getCostsExpensives(String year_month) {
        return this.registerRepository.getCostsExpensives(year_month);
    }

    public PercentualIncomeCostsDto getPercentualIncomeCosts(String year_month) {
        Object[][] result = this.registerRepository.getPercentualIncomeCosts(year_month);

        if (result != null) {
            Float total = result[0][0] != null ? ((Number) result[0][0]).floatValue() : null;
            Float income = result[0][1] != null ? ((Number) result[0][1]).floatValue() : null;
            Float costs = result[0][2] != null ? ((Number) result[0][2]).floatValue() : null;

            Float percentualIncome = Float.parseFloat(
                String.format("%.2f", (100 * income) / total).replace(",", ".")
                );
            Float percentualCosts =  Float.parseFloat(
                String.format("%.2f", ((100 * costs) / total) * -1).replace(",", ".")
                );

            return new PercentualIncomeCostsDto(percentualIncome, percentualCosts);
        }
        return new PercentualIncomeCostsDto(0.0f, 0.0f);
    }

    public List<Map<String, Float>> getCostsByMonth(){
        return this.registerRepository.getCostsByMonths();
    }

}
