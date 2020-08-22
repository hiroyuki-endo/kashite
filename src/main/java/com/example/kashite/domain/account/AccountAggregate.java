package com.example.kashite.domain.account;

import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.event.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String name;

    @Autowired
    private EncryptService encryptService;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd) {
        AccountCreatedEvent event =
                new AccountCreatedEvent(cmd.getId(), cmd.getName(), encryptService.encode(cmd.getPassword()));
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }
}
