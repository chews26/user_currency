package com.sparta.currency_user.entity;

import com.sparta.currency_user.enums.ExchangeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity
public class Exchange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(precision = 15, scale = 5)
    private BigDecimal amount_in_krw;

    private BigDecimal amount_after_exchange;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExchangeStatus status;

    protected Exchange() {
    }

    // DTO 변환용 생성자
    public Exchange(User user, BigDecimal amount_in_krw, Currency currency) {
        this.user = user;
        this.amount_in_krw = amount_in_krw;
        this.currency = currency;
        this.status = ExchangeStatus.NORMAL; // 기본 상태 설정
    }

    // 결과 값 저장용 Setter 메서드
    public void setAmountAfterExchange(BigDecimal amountAfterExchange) {
        this.amount_after_exchange = amountAfterExchange;
    }

    // 상태 변경 메서드 추가
    public void setStatus(ExchangeStatus status) {
        this.status = status;
    }
}
