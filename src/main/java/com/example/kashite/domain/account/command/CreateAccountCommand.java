package com.example.kashite.domain.account.command;

import com.example.kashite.framework.cqrs.Command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccountCommand implements Command {

    private String id;
    private long version;
    private String name;
    private String displayName;
    private String password;

    public CreateAccountCommand() {
        this.id = newId();
    }
}
