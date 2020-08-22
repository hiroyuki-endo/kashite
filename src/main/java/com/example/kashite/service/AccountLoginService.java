package com.example.kashite.service;

import com.example.kashite.query.model.account.AccountEntity;
import com.example.kashite.query.model.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountLoginService {

    @Autowired
    private AccountRepository accountRepository;

    public void checkSignInResult(String id) {
        AccountEntity entity = accountRepository.findById(id).orElse(null);
        if(Objects.isNull(entity)) {
            throw new IllegalStateException("not found");
        }
        if(entity.getFailedCount() > 0) {
            throw new IllegalStateException("failed");
        }
    }
}
