package com.gsTech.um_para_um.orm;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "tb_debitCard")
public class DebitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private LocalDate expirationDate;
    private Integer securityCode;
    private String cardFlag;

    @OneToOne(mappedBy = "debitCard")
    private BankAccount bankAccount;

    public DebitCard() {
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    public String getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
