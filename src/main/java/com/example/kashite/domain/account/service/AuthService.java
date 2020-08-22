package com.example.kashite.domain.account.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    Authentication authenticate(String name, String password);
}
