package com.example.kashite.domain.account.command;

import com.example.kashite.framework.cqrs.Command.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInAccountCommand implements Command {
    private String id;
    private long version;
    private String name;
    private String password;

    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
