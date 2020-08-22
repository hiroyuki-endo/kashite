package com.example.kashite.domain.account.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountCreatedEvent {

    private String id;
    private String name;
    private String password;

}
