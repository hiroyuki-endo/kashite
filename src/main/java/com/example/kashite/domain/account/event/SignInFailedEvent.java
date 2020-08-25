package com.example.kashite.domain.account.event;

import lombok.Value;

@Value
public class SignInFailedEvent {
    private String id;
    private long version;
}
