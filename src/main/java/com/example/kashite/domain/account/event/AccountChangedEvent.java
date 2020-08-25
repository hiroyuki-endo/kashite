package com.example.kashite.domain.account.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountChangedEvent {

    private String id;
    private long version;
    private String displayName;

}
