package com.example.kashite.domain.account.command;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignInAccountCommand extends AbstractCommand {
    private String id;
    private String name;
    private String password;

    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
