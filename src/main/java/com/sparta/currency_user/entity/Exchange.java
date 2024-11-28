package com.sparta.currency_user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Column(scale = 5)
    private BigDecimal amount_in_krw;

    private BigDecimal amount_after_exchange;

    private String status;

    protected Exchange() {}

    // DTO 변환용 생성자
    public Exchange(BigDecimal amount_in_krw, Currency currency, User user) {
        this.amount_in_krw = amount_in_krw;
        this.currency = currency;
        this.user = user;
        this.status = "normal"; // 기본 상태 설정
    }

    // 결과 값 저장용 Setter 메서드
    public void setAmountAfterExchange(BigDecimal amountAfterExchange) {
        this.amount_after_exchange = amountAfterExchange;
    }
}
