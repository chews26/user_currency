package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.exchange.ExchangeRequestDto;
import com.sparta.currency_user.dto.exchange.ExchangeResponseDto;
import com.sparta.currency_user.dto.exchange.ExchangeSummaryDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.Exchange;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.enums.ExchangeStatus;
import com.sparta.currency_user.exception.CustomErrorCode;
import com.sparta.currency_user.exception.CustomException;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRepository;
import com.sparta.currency_user.repository.UserRepository;
import com.sparta.currency_user.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final SessionUtils sessionUtils;

    // 환전 요청 및 처리
    @Transactional
    public ExchangeResponseDto exchange(ExchangeRequestDto exchangeRequestDto) throws CustomException {

        // 로그인된 사용자 이메일로 조회
        String email = sessionUtils.getLoginUserEmail();
        User findUser = userRepository.findByEmailOrElseThrow(email);
        // 예외 처리
        if (email == null || email.isEmpty()) {
            throw new CustomException(CustomErrorCode.POST_FORBIDDEN);
        }
        // 환율 정보 조회 및 예외처리
        Currency currency = currencyRepository.findByCurrencyName(exchangeRequestDto.getCurrency_name())
                .orElseThrow(() -> new CustomException(CustomErrorCode.CURRENCY_INCORRECT));
        // 환전 계산
        BigDecimal amountInKrw = exchangeRequestDto.getAmount_in_krw();
        BigDecimal exchangeRate = currency.getExchangeRate();
        BigDecimal amountAfterExchange = amountInKrw.multiply(exchangeRate);
        // Exchange 엔티티 생성
        Exchange exchange = new Exchange(findUser,exchangeRequestDto.getAmount_in_krw(), currency);
        exchange.setAmountAfterExchange(amountAfterExchange);
        // 데이터 저장
        Exchange savedExchange = exchangeRepository.save(exchange);
        // 응답 DTO 생성 및 반환
        return ExchangeResponseDto.toEntity(savedExchange);
    }

    // 전체 환전 요청 조회
    @Transactional(readOnly = true)
    public List<ExchangeResponseDto> getAllExchanges() {
        return exchangeRepository.findAll().stream()
                .map(ExchangeResponseDto::toEntity)
                .collect(Collectors.toList());    }

    // 모든 환전 요청을 그룹화하여 조회
    @Transactional(readOnly = true)
    public List<ExchangeSummaryDto> getExchangeSummaries() {
        return exchangeRepository.findExchangeSummaries();
    }

    // 환전 요청 상태 취소
    public ExchangeResponseDto updateStatus(Long id, String exchangeId) {
        // 환전 요청 조회
        Exchange exchange = exchangeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("환전 요청을 찾을 수 없습니다: " + exchangeId));
        // 상태 변경
        exchange.setStatus(ExchangeStatus.CANCELLED);
        // 데이터 저장
        Exchange updatedExchange = exchangeRepository.save(exchange);
        // 응답 DTO 생성 및 반환
        return ExchangeResponseDto.toEntity(updatedExchange);
    }
}
