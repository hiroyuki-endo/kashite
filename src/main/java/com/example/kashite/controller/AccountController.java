package com.example.kashite.controller;

import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.command.DeleteAccountCommand;
import com.example.kashite.domain.account.command.FailedAccountCommand;
import com.example.kashite.domain.account.command.SignInAccountCommand;
import com.example.kashite.framework.cqrs.CommandExecutor;
import com.example.kashite.query.model.account.AccountEntity;
import com.example.kashite.query.model.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private CommandExecutor executor;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("accounts")
    public List<AccountEntity> all() {
        return accountRepository.findAll();
    }

    @GetMapping("accounts/{accountId}")
    public AccountEntity one(@PathVariable("accountId") String accountId) {
        return accountRepository.findById(accountId).get();
    }

    @PostMapping("accounts")
    public String create(@RequestBody CreateAccountCommand cmd) {
        return executor.execute(cmd);
    }

    @DeleteMapping("accounts")
    public String delete(@RequestBody DeleteAccountCommand cmd) {
        return executor.execute(cmd);
    }

    @PostMapping("sign-in")
    public void signIn(@RequestBody SignInAccountCommand cmd) {
        try {
            executor.execute(cmd);
        } catch (Exception ex) {
            executor.execute(new FailedAccountCommand(cmd.getId(), cmd.getVersion()));
            throw ex;
        }
    }
}
