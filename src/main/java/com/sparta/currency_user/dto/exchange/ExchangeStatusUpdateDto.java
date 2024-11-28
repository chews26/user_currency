package com.sparta.currency_user.dto.exchange;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExchangeStatusUpdateDto {

    @NotBlank
    private String status; // 상태 필드만 포함

}
