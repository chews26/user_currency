package com.sparta.currency_user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotBlank
    private String currencyName;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00000")
    private BigDecimal exchangeRate;

    @NotBlank
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
