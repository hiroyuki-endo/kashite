package com.example.kashite.query.materializer;

import com.example.kashite.adapter.dao.AuthorDao;
import com.example.kashite.adapter.dao.BookAuthorDao;
import com.example.kashite.adapter.dao.BookInfoDao;
import com.example.kashite.domain.bookinfo.event.BookInfoCreatedEvent;
import com.example.kashite.domain.bookinfo.event.BookWroteEvent;
import com.example.kashite.query.entity.AuthorEntity;
import com.example.kashite.query.entity.BookAuthorEntity;
import com.example.kashite.query.entity.BookInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookInfoMaterializer {

    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private BookAuthorDao bookAuthorDao;

    @EventSourcingHandler
    public void createBookInfo(BookInfoCreatedEvent event) {
        BookInfoEntity bookInfoEntity = new BookInfoEntity(
                event.getId(),
                event.getIsbn(),
                event.getTitle(),
                event.getPublisher(),
                event.getPublishedDate(),
                event.getDescription());
        log.debug("create bookInfo table of {}", bookInfoEntity);
        bookInfoDao.save(bookInfoEntity);
    }

    @EventSourcingHandler
    public void createAuthor(BookWroteEvent event) {
        BookAuthorEntity entity = new BookAuthorEntity(event.getId(), event.getBookInfoId(), event.getAuthor());
        bookAuthorDao.save(entity);
    }
}
