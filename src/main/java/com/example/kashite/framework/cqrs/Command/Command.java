package com.example.kashite.framework.cqrs.Command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateVersion;

import java.util.UUID;

public interface Command {
    default String newId() {
        return UUID.randomUUID().toString();
    }

    @TargetAggregateIdentifier
    default String aggregateIdentifier() {
        return getId();
    }

    @TargetAggregateVersion
    default long aggregateVersion(){
        return getVersion();
    };

    String getId();

    long getVersion();
}
