package com.example.kashite.domain.account;

import com.example.kashite.domain.DomainTest;
import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.command.SignInAccountCommand;
import com.example.kashite.domain.account.event.AccountCreatedEvent;
import com.example.kashite.domain.account.event.SignInSucceededEvent;
import com.example.kashite.domain.account.service.AuthService;
import com.example.kashite.domain.account.service.EncryptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AccountAggregateTest extends DomainTest<AccountAggregate> {

    @Mock
    private EncryptService encryptService;

    @Mock
    private AuthService authService;

    @Override
    protected Class<AccountAggregate> targetClass() {
        return AccountAggregate.class;
    }

    @Override
    public void setUp() {
        super.setUp();
        fixture().registerInjectableResource(encryptService);
        fixture().registerInjectableResource(authService);
    }

    @Test
    public void createAccount() {
        when(encryptService.encode("testPassword")).thenReturn("encryptedPassword");

        fixture().given()
                .when(new CreateAccountCommand("accountId", 0, "testName", "testPassword"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AccountCreatedEvent("accountId", "testName", "encryptedPassword"));
    }

    @Test
    public void signInSucceed() {
        fixture().given(new AccountCreatedEvent("accountId", "testName", "encryptedPassword"))
                .when(new SignInAccountCommand("accountId", 0, "testName", "testPassword"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new SignInSucceededEvent("accountId"));
    }

    @Test
    public void signInFailed() {
        when(authService.authenticate("testName", "failedPassword")).thenThrow(new ArithmeticException());

        fixture().given(new AccountCreatedEvent("accountId", "testName", "encryptedPassword"))
                .when(new SignInAccountCommand("accountId", 0, "testName", "failedPassword"))
                .expectException(ArithmeticException.class);
    }
}
