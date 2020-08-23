package com.example.kashite.adapter.domain;

import com.example.kashite.domain.account.service.AccountChecker;
import com.example.kashite.query.model.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCheckerImpl implements AccountChecker {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void checkNotExistsName(String name) {
        if(accountRepository.existsByName(name)) {
            throw new IllegalStateException(name);
        }
    }
}
