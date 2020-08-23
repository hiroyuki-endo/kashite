package com.example.kashite.query.materializer;

import com.example.kashite.domain.account.event.AccountCreatedEvent;
import com.example.kashite.domain.account.event.AccountDeletedEvent;
import com.example.kashite.domain.account.event.SignInFailedEvent;
import com.example.kashite.domain.account.event.SignInSucceededEvent;
import com.example.kashite.query.model.account.AccountEntity;
import com.example.kashite.query.model.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountMaterializer {
    @Autowired
    private AccountRepository accountRepository;

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        accountRepository.save(new AccountEntity(event.getId(), event.getName(), event.getPassword(), 0));
    }

    @EventSourcingHandler
    public void on(SignInSucceededEvent event) {
        AccountEntity entity = accountRepository.findById(event.getId()).orElse(null);
        entity.setFailedCount(0);
        accountRepository.save(entity);
    }

    @EventSourcingHandler
    public void on(SignInFailedEvent event) {
        AccountEntity entity = accountRepository.findById(event.getId()).orElse(null);
        entity.setFailedCount(entity.getFailedCount() + 1);
        accountRepository.save(entity);
    }

    @EventSourcingHandler
    public void on(AccountDeletedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
