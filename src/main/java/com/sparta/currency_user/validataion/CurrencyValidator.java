package com.sparta.currency_user.validataion;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CurrencyValidator {

    private final CurrencyRepository currencyRepository;

    @PostConstruct
    public void validateCurrencies() {
        log.info("CurrencyValidator: 통화 데이터 유효성 검사 시작");

        // 모든 통화 데이터를 조회
        List<Currency> currencies = currencyRepository.findAll();

        for (Currency currency : currencies) {
            BigDecimal exchangeRate = currency.getExchangeRate();
            if (exchangeRate.compareTo(BigDecimal.valueOf(0.01)) < 0 || exchangeRate.compareTo(BigDecimal.valueOf(1000)) > 0) {
                log.warn("유효하지 않은 통화 발견 - 이름: {}, 환율: {}", currency.getCurrencyName(), exchangeRate);
            }
        }
        log.info("CurrencyValidator: 통화 데이터 유효성 검사 완료");
    }
}
