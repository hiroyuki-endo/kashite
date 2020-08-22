package com.example.kashite.controller;

import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.framework.cqrs.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private CommandExecutor executor;

    @PostMapping("accounts")
    public String createAccount(@RequestBody CreateAccountCommand cmd) {
        return executor.execute(cmd);
    }
}