package me.dio.teste.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Column(precision = 2, scale = 13)
    private BigDecimal limit;

    // Getters e Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getLimit() {
        return limit;
    }
    @Column(precision = 2, scale = 13, name = "card_limit")
    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
