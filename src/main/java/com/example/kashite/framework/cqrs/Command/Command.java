package com.example.kashite.framework.cqrs.Command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateVersion;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public interface Command {
    default String newId() {
        return UUID.randomUUID().toString();
    }

    @TargetAggregateIdentifier
    String aggregateIdentifier();

    @TargetAggregateVersion
    long aggreateVersion();
}
