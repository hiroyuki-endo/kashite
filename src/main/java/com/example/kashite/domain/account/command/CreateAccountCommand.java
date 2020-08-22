package com.example.kashite.domain.account.command;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccountCommand extends AbstractCommand {

    private String id;
    private String name;
    private String password;

    public CreateAccountCommand() {
        this.id = newId();
    }

    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
