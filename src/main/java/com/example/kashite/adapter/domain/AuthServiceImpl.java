package com.example.kashite.adapter.domain;

import com.example.kashite.domain.account.service.AuthService;
import com.example.kashite.domain.account.service.EncryptService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private PasswordEncoder encryptService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder encryptService) {
        this.authenticationManager = authenticationManager;
        this.encryptService = encryptService;
    }

    @Override
    public Authentication authenticate(String name, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        name,
                        password,
                        new ArrayList<>()
                )
        );
    }
}
