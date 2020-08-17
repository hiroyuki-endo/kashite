package com.example.kashite.query.materializer;

import com.example.kashite.adapter.dao.AuthorDao;
import com.example.kashite.domain.author.event.AuthorCreatedEvent;
import com.example.kashite.query.entity.AuthorEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorMaterializer {

    @Autowired
    private AuthorDao authorDao;

    @EventSourcingHandler
    public void createAuthor(AuthorCreatedEvent event) {
        AuthorEntity entity = new AuthorEntity(event.getId(), event.getName());
        authorDao.save(entity);
    }
}
