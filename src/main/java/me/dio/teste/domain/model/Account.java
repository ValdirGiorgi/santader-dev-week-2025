package me.dio.teste.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_account")
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String number;
    private String agency;
    private Double balance;
    
    @Column(name = "account_limit") // Usando nome diferente para evitar palavra reservada SQL
    private Double limit;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getAgency() {
        return agency;
    }
    
    public void setAgency(String agency) {
        this.agency = agency;
    }
    
    public Double getBalance() {
        return balance;
    }
    
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public Double getLimit() {
        return limit;
    }
    
    public void setLimit(Double limit) {
        this.limit = limit;
    }
}
