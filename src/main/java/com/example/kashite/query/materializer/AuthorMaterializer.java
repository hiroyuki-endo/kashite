package com.example.kashite.query.materializer;

import com.example.kashite.domain.author.event.AuthorCreatedEvent;
import com.example.kashite.query.model.author.AuthorRepository;
import com.example.kashite.query.model.author.AuthorEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorMaterializer {

    @Autowired
    private AuthorRepository authorRepository;

    @EventSourcingHandler
    public void createAuthor(AuthorCreatedEvent event) {
        AuthorEntity entity = new AuthorEntity(event.getId(), event.getName());
        authorRepository.save(entity);
    }
}
