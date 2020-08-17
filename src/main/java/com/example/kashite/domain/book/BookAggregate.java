package com.example.kashite.domain.book;

import com.example.kashite.domain.book.command.RegisterBookCommand;
import com.example.kashite.domain.book.event.BookRegisteredEvent;
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
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String bookInfoId;
    private BookStatus status;
    private String lenderId;
    private String borrowerId;

    @CommandHandler
    public BookAggregate(RegisterBookCommand cmd) {
        AggregateLifecycle.apply(BookRegisteredEvent.from(cmd));
    }

    @EventSourcingHandler
    public void on(BookRegisteredEvent event) {
        this.id = event.getId();
        this.bookInfoId = event.getBookInfoId();
        this.status = event.getStatus();
        this.lenderId = event.getLenderId();
        log.info(event.toString());
    }
}
