package com.example.kashite.domain.account;

import com.example.kashite.domain.account.command.*;
import com.example.kashite.domain.account.event.*;
import com.example.kashite.domain.account.service.AccountChecker;
import com.example.kashite.domain.account.service.AuthService;
import com.example.kashite.domain.account.service.EncryptService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String displayName;
    private int filedCount;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand cmd, AccountChecker accountChecker , EncryptService encryptService) {
        accountChecker.checkNotExistsName(cmd.getName());

        AccountCreatedEvent event =
                new AccountCreatedEvent(cmd.getId(), cmd.getName(),
                        cmd.getDisplayName(), encryptService.encode(cmd.getPassword()));
        apply(event);
    }

    @CommandHandler
    public void handle(SignInAccountCommand cmd, AuthService authService) {
        authService.authenticate(cmd.getName(), cmd.getPassword());
        apply(new SignInSucceededEvent(cmd.getId(), cmd.getVersion()));
    }

    @CommandHandler
    public void handle(ChangeAccountCommand cmd) {
        apply(new AccountChangedEvent(cmd.getId(), cmd.getVersion(), cmd.getDisplayName()));
    }

    @CommandHandler
    public void handle(ChangePasswordCommand cmd, EncryptService encryptService) {
        apply(new PasswordChangedEvent(cmd.getId(), cmd.getVersion(), encryptService.encode(cmd.getPassword())));
    }

    @CommandHandler
    public void handle(FailedAccountCommand cmd) {
        apply(new SignInFailedEvent(cmd.getId(), cmd.getVersion()));
    }

    @CommandHandler
    public void handle(DeleteAccountCommand cmd) {
        apply(new AccountDeletedEvent(cmd.getId(), cmd.getVersion()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        if(Objects.isNull(event.getDisplayName())) {
            // 表示名が無い場合は、ユーザー名を設定する
            this.displayName = this.name;
        } else {
            this.displayName = event.getDisplayName();
        }
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
    public void on(AccountChangedEvent event) {
        this.displayName = event.getDisplayName();
    }

    @EventSourcingHandler
    public void on(AccountDeletedEvent event) {
        markDeleted();
    }
}
