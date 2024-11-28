package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.exchange.ExchangeRequestDto;
import com.sparta.currency_user.dto.exchange.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    // 환전 요청 및 처리
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> exchange(
            @Valid @RequestBody ExchangeRequestDto exchangeRequestDto
    ) {
        ExchangeResponseDto exchangeResponseDto = exchangeService.exchange(exchangeRequestDto);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.CREATED);
    }

    // 전체 환전 요청 조회
    @GetMapping
    public ResponseEntity<List<ExchangeResponseDto>> getAllExchanges() {
        List<ExchangeResponseDto> exchangeResponseDtoList = exchangeService.getAllExchanges();
        return new ResponseEntity<>(exchangeResponseDtoList, HttpStatus.OK);
    }

    // 환전 요청 상태 취소
    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> patchExchange(
            @PathVariable Long id,
            @Valid @RequestBody ExchangeRequestDto exchangeRequestDto
    ) {
        ExchangeResponseDto exchangeResponseDto = exchangeService.cancelExchange(id);
        return new ResponseEntity<>(exchangeResponseDto, HttpStatus.OK);
    }
}
