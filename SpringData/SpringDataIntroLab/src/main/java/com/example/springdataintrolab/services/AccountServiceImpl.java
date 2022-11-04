package com.example.springdataintrolab.services;

import com.example.springdataintrolab.models.Account;
import com.example.springdataintrolab.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new RuntimeException("Account does not exist");
        }

        BigDecimal current = account.get().getBalance();

        if (amount.compareTo(current) > 0) {
            throw new RuntimeException("Insufficient amount");
        }

        account.get().setBalance(current.subtract(amount));

        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal amount, Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No account"));

        BigDecimal balance = account.getBalance().add(amount);
        account.setBalance(balance);

        this.accountRepository.save(account);
    }
}
