package com.example.kashite.domain.reader;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.kashite.domain.reader.command.CreateReaderCommand;
import com.example.kashite.domain.reader.event.ReaderCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Reader {
    @AggregateIdentifier
    private String id;
    private String name;

    @CommandHandler
    public Reader(CreateReaderCommand cmd) {
        AggregateLifecycle.apply(new ReaderCreatedEvent(cmd.getId(), cmd.getName()));
    }

    @EventSourcingHandler
    public void on(ReaderCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        log.info(event.toString());
    }
}
