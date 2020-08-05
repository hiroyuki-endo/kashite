package com.example.kashite.domain.bookinfo;


import java.util.Arrays;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import com.example.kashite.domain.bookinfo.event.BookInfoCreatedEvent;
import com.example.kashite.domain.bookinfo.event.BookWroteEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aggregate
@NoArgsConstructor
@Slf4j
public class BookInfo {
    @AggregateIdentifier
    private String id;
    private String isbn;
    private String title;
    private String[] authors;
    private String publisher;
    private String publishedDate;
    private String description;

    @CommandHandler
    public BookInfo(CreateBookInfoCommand cmd) {
        AggregateLifecycle.apply(BookInfoCreatedEvent.fromCommnad(cmd));
        for(String author : cmd.getAuthors()) {
            AggregateLifecycle.apply(new BookWroteEvent(cmd.getId(), author));
        }
    }

    @EventSourcingHandler
    public void on(BookInfoCreatedEvent event) {
        this.id = event.getId();
        log.info(event.toString());
    }
}
