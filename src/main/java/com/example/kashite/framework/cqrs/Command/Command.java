package com.example.kashite.framework.cqrs.Command;

import java.util.UUID;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.commandhandling.TargetAggregateVersion;

public interface Command {
    default String newId() {
        return UUID.randomUUID().toString();
    }

    @TargetAggregateIdentifier
    String aggregateIdentifier();

    @TargetAggregateVersion
    long aggreateVersion();
}
