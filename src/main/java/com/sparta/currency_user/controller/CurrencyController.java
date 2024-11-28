package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.currency.CurrencyRequestDto;
import com.sparta.currency_user.dto.currency.CurrencyResponseDto;
import com.sparta.currency_user.exception.CustomException;
import com.sparta.currency_user.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    // 모든 통화 조회
    @GetMapping
    public ResponseEntity<List<CurrencyResponseDto>> findCurrencies(
    ) throws CustomException  {
        return ResponseEntity.ok().body(currencyService.findAll());
    }

    // 특정 통화 조회
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> findCurrency(@PathVariable Long id
    ) throws CustomException {
        return ResponseEntity.ok().body(currencyService.findById(id));
    }

    // 새 통화 생성
    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(
            @Valid
            @RequestBody CurrencyRequestDto currencyRequestDto
    ) throws CustomException  {
        return ResponseEntity.ok().body(currencyService.save(currencyRequestDto));
    }
}
