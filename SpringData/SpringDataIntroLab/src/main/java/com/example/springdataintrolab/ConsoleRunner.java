package com.example.springdataintrolab;

import com.example.springdataintrolab.models.User;
import com.example.springdataintrolab.services.AccountService;
import com.example.springdataintrolab.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // this.userService.register("Joro", 33);

        User user = this.userService.findByUsername("Joro");
        this.accountService.depositMoney(BigDecimal.TEN, user.getId());
        this.accountService.withdrawMoney(BigDecimal.ONE, user.getId());
    }
}
