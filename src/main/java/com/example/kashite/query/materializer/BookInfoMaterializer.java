package com.example.kashite.query.materializer;

import com.example.kashite.domain.bookinfo.event.BookInfoCreatedEvent;
import com.example.kashite.domain.bookinfo.event.BookWroteEvent;
import com.example.kashite.query.model.bookauthor.BookAuthorRepository;
import com.example.kashite.query.model.bookauthor.BookAuthorEntity;
import com.example.kashite.query.model.bookinfo.BookInfoRepository;
import com.example.kashite.query.model.bookinfo.BookInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookInfoMaterializer {

    @Autowired
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @EventSourcingHandler
    public void createBookInfo(BookInfoCreatedEvent event) {
        BookInfoEntity bookInfoEntity = new BookInfoEntity(
                event.getId(),
                event.getIsbn(),
                event.getTitle(),
                event.getPublisher(),
                event.getPublishedDate(),
                event.getDescription(),
                event.getImageLink());
        bookInfoRepository.save(bookInfoEntity);
        log.debug("Added bookInfo table");
    }

    @EventSourcingHandler
    public void createAuthor(BookWroteEvent event) {
        BookAuthorEntity entity = new BookAuthorEntity(event.getId(), event.getBookInfoId(), event.getAuthor());
        bookAuthorRepository.save(entity);
        log.debug("Added author table");
    }
}
