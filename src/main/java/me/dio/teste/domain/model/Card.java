package me.dio.teste.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_card")

public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Column(precision = 13, scale = 2)
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
    @Column(precision = 13, scale = 2, name = "card_limit")
    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
