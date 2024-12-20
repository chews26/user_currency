package com.sparta.currency_user.dto.exchange;

import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.enums.ExchangeStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ExchangeResponseDto {

    private final Long id;

    private final String name;

    private final String status;

    private final BigDecimal amount_after_exchange;

    private final String currency_name;

    private final LocalDateTime created_at;

    private final LocalDateTime modified_at;

    public ExchangeResponseDto(Long id, String name, ExchangeStatus status, BigDecimal amount_after_exchange, String currency_name, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.name = name;
        this.status = String.valueOf(status);
        this.amount_after_exchange = amount_after_exchange;
        this.currency_name = currency_name;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public static ExchangeResponseDto toEntity(Exchange exchange) {
        return new ExchangeResponseDto(
                exchange.getId(),
                exchange.getUser().getName(),
                exchange.getStatus(),
                exchange.getAmount_after_exchange(),
                exchange.getCurrency().getCurrencyName(),
                exchange.getCreatedAt(),
                exchange.getModifiedAt()
        );
    }
}


