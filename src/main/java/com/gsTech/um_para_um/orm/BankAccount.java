package com.gsTech.um_para_um.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_bankAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "bankAccount")
    private Holder holder;
    private Integer agency;
    private String accountNumber;
    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.ALL)
    private DebitCard debitCard;

    public BankAccount() {
    }

    public BankAccount(Integer agency, String accountNumber, BigDecimal balance, DebitCard debitCard) {
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.debitCard = debitCard;
    }

    public Holder getHolder() {
        return holder;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }


    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
    }
}
