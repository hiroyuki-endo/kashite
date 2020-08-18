package com.example.kashite.domain.bookinfo;


import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import com.example.kashite.domain.bookinfo.event.BookInfoCreatedEvent;
import com.example.kashite.domain.bookinfo.event.BookWroteEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Slf4j
public class BookInfo {
    @AggregateIdentifier
    private String id;
    private String isbn;
    private String title;
    private List authors = new ArrayList();
    private String publisher;
    private String publishedDate;
    private String description;
    private String imageLink;

    @CommandHandler
    public BookInfo(CreateBookInfoCommand cmd) {
        AggregateLifecycle.apply(BookInfoCreatedEvent.fromCommnad(cmd));
        for(String author : cmd.getAuthors()) {
            AggregateLifecycle.apply(new BookWroteEvent(UUID.randomUUID().toString(), cmd.getId(), author));
        }
    }

    @EventSourcingHandler
    public void on(BookInfoCreatedEvent event) {
        this.id = event.getId();
        this.isbn = event.getIsbn();
        this.title = event.getTitle();
        this.publisher = event.getPublisher();
        this.publishedDate = event.getPublishedDate();
        this.description = event.getDescription();
        log.info(event.toString());
    }

    @EventSourcingHandler
    public void on(BookWroteEvent evnet) {
        this.authors.add(evnet.getAuthor());
    }
}
