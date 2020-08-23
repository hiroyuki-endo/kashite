package com.example.kashite.domain.account;

import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.command.DeleteAccountCommand;
import com.example.kashite.domain.account.command.FailedAccountCommand;
import com.example.kashite.domain.account.command.SignInAccountCommand;
import com.example.kashite.domain.account.event.AccountCreatedEvent;
import com.example.kashite.domain.account.event.AccountDeletedEvent;
import com.example.kashite.domain.account.event.SignInFailedEvent;
import com.example.kashite.domain.account.event.SignInSucceededEvent;
import com.example.kashite.domain.account.service.AccountChecker;
import com.example.kashite.domain.account.service.AuthService;
import com.example.kashite.domain.account.service.EncryptService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private int filedCount;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd, AccountChecker accountChecker , EncryptService encryptService) {
        accountChecker.checkNotExistsName(cmd.getName());

        AccountCreatedEvent event =
                new AccountCreatedEvent(cmd.getId(), cmd.getName(), encryptService.encode(cmd.getPassword()));
        apply(event);
    }

    @CommandHandler
    public void handle(SignInAccountCommand cmd, AuthService authService) {
        authService.authenticate(cmd.getName(), cmd.getPassword());
        apply(new SignInSucceededEvent(cmd.getId()));
    }

    @CommandHandler
    public void handle(FailedAccountCommand cmd) {
        apply(new SignInFailedEvent(cmd.getId()));
    }

    @CommandHandler
    public void handle(DeleteAccountCommand cmd) {
        apply(new AccountDeletedEvent(cmd.getId()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }

    @EventSourcingHandler
    public void on(SignInSucceededEvent event) {
        this.filedCount = 0;
    }

    @EventSourcingHandler
    public void on(SignInFailedEvent event) {
        this.filedCount++;
    }

    @EventSourcingHandler
    public void on(AccountDeletedEvent event) {
        markDeleted();
    }
}
