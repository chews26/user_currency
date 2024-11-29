package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.exchange.ExchangeSummaryDto;
import com.sparta.currency_user.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    // exchangeId 존재 유무 확인
    default Exchange findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 환전요청입니다." + id));
    }

    // 모든 환전 요청을 그룹화하여 조회
    @Query("SELECT new com.sparta.currency_user.dto.exchange.ExchangeSummaryDto(COUNT(e), SUM(e.amount_in_krw)) " +
            "FROM Exchange e GROUP BY e.user.id")
    List<ExchangeSummaryDto> findExchangeSummaries();

}