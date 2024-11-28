package com.sparta.currency_user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExchangeRequestDto {

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00000")
    private final BigDecimal amount_in_krw;

    @NotBlank
    private final String currency_name;

    public ExchangeRequestDto(BigDecimal amount_in_krw, String currency_name) {
        this.amount_in_krw = amount_in_krw;
        this.currency_name = currency_name;
    }

    public Exchange toEntity(User user, Currency currency) {
        return new Exchange(
                this.amount_in_krw,
                currency,
                user
        );
    }
}
