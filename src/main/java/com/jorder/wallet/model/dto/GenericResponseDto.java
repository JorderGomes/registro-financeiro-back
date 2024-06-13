package com.jorder.wallet.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseDto<T> {
    
    private String message;
    private T data;

    public GenericResponseDto(String message, T data) {
        this.message = message;
        this.data = data;
    }

}
