package com.sparta.currency_user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.currency_user.entity.Exchange;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {

    private final String id;

    private final String name;

    private final String status;

    private final BigDecimal amount_after_exchange;

    private final String currency_name;

    private final LocalDateTime created_at;

    private final LocalDateTime modified_at;

    public ExchangeResponseDto(String id, String name, String status, BigDecimal amount_after_exchange, String currency_name, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.amount_after_exchange = amount_after_exchange;
        this.currency_name = currency_name;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }
}


