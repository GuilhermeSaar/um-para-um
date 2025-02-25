package com.gsTech.um_para_um.orm;

import com.gsTech.um_para_um.enums.Flag;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "tb_debitCard")
public class DebitCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cardNumber;
    private LocalDate expirationDate;
    private Integer securityCode;
    @Enumerated(EnumType.STRING)
    private Flag cardFlag;

    @OneToOne(mappedBy = "debitCard")
    private BankAccount bankAccount;

    public DebitCard() {
    }

    public DebitCard(Long cardNumber, LocalDate expirationDate, Integer securityCode, Flag cardFlag) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.cardFlag = cardFlag;
    }

    public Long getId() {
        return id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
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

    public Flag getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(Flag cardFlag) {
        this.cardFlag = cardFlag;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
