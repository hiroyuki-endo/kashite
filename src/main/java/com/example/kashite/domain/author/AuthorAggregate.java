package com.example.kashite.domain.author;

import com.example.kashite.domain.author.command.CreateAuthorCommand;
import com.example.kashite.domain.author.event.AuthorCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
public class AuthorAggregate {
    @AggregateIdentifier
    String id;
    String name;

    @CommandHandler
    public AuthorAggregate(CreateAuthorCommand cmd) {
        AggregateLifecycle.apply(AuthorCreatedEvent.from(cmd));
    }

    @EventSourcingHandler
    public void on(AuthorCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
    }
}
