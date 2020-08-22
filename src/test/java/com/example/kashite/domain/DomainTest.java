package com.example.kashite.domain;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;

public abstract class DomainTest<T> {
    private FixtureConfiguration<T> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(targetClass());
    }

    protected FixtureConfiguration<T> fixture() {
        return this.fixture;
    }

    protected abstract Class<T> targetClass();
}
