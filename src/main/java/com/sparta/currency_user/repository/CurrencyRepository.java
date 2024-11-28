package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    // 통화명 존재 유무 확인
    Optional<Currency> findByCurrencyName(String currencyName);
    default Currency findByCurrencyNameOrElseThrow(String currencyName) {
        return findByCurrencyName(currencyName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 통화명입니다"));
    }
}
