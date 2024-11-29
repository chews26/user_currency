package com.sparta.currency_user.dto.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ExchangeSummaryDto {

    //환전 요청 데이터들의 총 Row 수
    private Long count;

    // 환전을 요청한 총 금액
    private BigDecimal total;


}
