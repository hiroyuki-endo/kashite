package com.example.kashite.domain.account;

import com.example.kashite.domain.account.command.CreateAccountCommand;
import com.example.kashite.domain.account.event.AccountCreatedEvent;
import com.example.kashite.domain.account.service.EncryptService;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountAggregateTest {
    private FixtureConfiguration<AccountAggregate> fixture;

    @Mock
    private EncryptService encryptService;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(AccountAggregate.class);
        fixture.registerInjectableResource(encryptService);
    }

    @Test
    public void createAccount() {
        when(encryptService.encode("testPassword")).thenReturn("encryptedPassword");

        fixture.given()
                .when(new CreateAccountCommand("accountId", "testName", "testPassword"))
                .expectSuccessfulHandlerExecution()
                .expectEvents(new AccountCreatedEvent("accountId", "testName", "encryptedPassword"));
    }
}
