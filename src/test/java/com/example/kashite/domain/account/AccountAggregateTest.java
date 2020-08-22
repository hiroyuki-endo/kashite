package com.example.kashite.domain.account;

import com.example.kashite.domain.DomainTest;
import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.event.AccountCreatedEvent;
import com.example.kashite.domain.account.service.EncryptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountAggregateTest extends DomainTest<AccountAggregate> {

    @Mock
    private EncryptService encryptService;

    @Override
    protected Class<AccountAggregate> targetClass() {
        return AccountAggregate.class;
    }

    @Override
    public void setUp() {
        super.setUp();
        fixture().registerInjectableResource(encryptService);
    }

    @Test
    public void createAccount() {
        when(encryptService.encode("testPassword")).thenReturn("encryptedPassword");

        fixture().given()
                .when(new CreateAccountCommand("accountId", "testName", "testPassword"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AccountCreatedEvent("accountId", "testName", "encryptedPassword"));
    }
}
