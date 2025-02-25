package com.gsTech.um_para_um.service;

import com.gsTech.um_para_um.DTO.RegisterDTO;
import com.gsTech.um_para_um.Repository.BankAccountRepository;
import com.gsTech.um_para_um.Repository.HolderRepository;
import com.gsTech.um_para_um.enums.Flag;
import com.gsTech.um_para_um.orm.BankAccount;
import com.gsTech.um_para_um.orm.DebitCard;
import com.gsTech.um_para_um.orm.Holder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

@Service
public class RegisterService {

    @Autowired
    private HolderRepository holderRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public boolean registerUser(RegisterDTO data) {

        String numberAccount = generateNumberAccount();

        if(!this.bankAccountRepository.existsByAccountNumber(numberAccount) && (this.holderRepository.findByEmail(data.getEmail()) == null)) {

            BankAccount account = new BankAccount();
            account.setAgency(2433);
            account.setAccountNumber(numberAccount);
            account.setBalance(BigDecimal.valueOf(0));

            DebitCard debitCard = new DebitCard(generateNumberCard(), LocalDate.now().plusYears(5),
                    generateCVV(), generateFlag());

            account.setDebitCard(debitCard);
            this.bankAccountRepository.save(account);

            String encryptPassword = passwordEncoder.encode(data.getPassword());

            holderRepository.save(new Holder(data.getName(), data.getAge()
                    ,data.getEmail(), encryptPassword, data.getRole(), account));

            return true;

        } else return false;
    }

    private String generateNumberAccount() {

        Random random = new Random();

        int number = random.nextInt(1_000_0000);
        String formattedNumber = String.format("%07d", number);
        int digit = random.nextInt(5);

        return formattedNumber + "-" + digit;
    }

    private Long generateNumberCard() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(9) + 1; // Garante que seja de 1 a 9, excluindo 0
            cardNumber.append(digit);
        }

        return Long.parseLong(cardNumber.toString());
    }

    private Integer generateCVV() {

        SecureRandom secureRandom = new SecureRandom();

        return secureRandom.nextInt(1000);
    }

    private Flag generateFlag() {

        Random random = new Random();
        Flag[] flags = Flag.values();
        int randomFlag = random.nextInt(flags.length);
        return flags[randomFlag];
    }
}