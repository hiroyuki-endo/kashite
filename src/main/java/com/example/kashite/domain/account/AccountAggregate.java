package com.example.kashite.domain.account;

import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.command.SignInAccountCommand;
import com.example.kashite.domain.account.event.AccountCreatedEvent;
import com.example.kashite.domain.account.service.EncryptService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private int filedCount;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd, EncryptService encryptService) {
        AccountCreatedEvent event =
                new AccountCreatedEvent(cmd.getId(), cmd.getName(), encryptService.encode(cmd.getPassword()));
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void signIn(SignInAccountCommand cmd) {

    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event, EncryptService encryptService) {
        this.id = event.getId();
        this.name = event.getName();
    }
}
