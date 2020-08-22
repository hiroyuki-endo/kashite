package com.example.kashite.adapter.service;

import com.example.kashite.query.model.account.AccountEntity;
import com.example.kashite.query.model.account.AccountRepository;
import com.example.kashite.service.auth.UserDetailsDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<AccountEntity> accounts = accountRepository.findByName(username);
        if(accounts.size() != 1) {
            throw new UsernameNotFoundException("");
        }
        AccountEntity account = accounts.get(0);
        UserDetailsDto dto = new UserDetailsDto(account);
        return dto;
    }
}
