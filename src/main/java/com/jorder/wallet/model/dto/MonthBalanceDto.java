package com.jorder.wallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthBalanceDto {
    
    private String month;
    private float general;
    private float spent;
    private float income;

}
