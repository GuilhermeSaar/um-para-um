package com.gsTech.um_para_um.orm;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_bankAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String holder;
    private Integer agency;
    private String accountNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private DebitCard debitCard;

    public BankAccount() {
    }

    public Long getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
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
}
