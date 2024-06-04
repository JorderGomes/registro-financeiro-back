package com.jorder.wallet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PercentualIncomeCostsDto {

    // private float total;
    private float percentIncome;
    private float percentCosts;
    
}
