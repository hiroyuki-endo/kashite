package com.example.kashite.service;

import com.example.kashite.adapter.dao.AuthorDao;
import com.example.kashite.domain.author.command.CreateAuthorCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import com.example.kashite.domain.search.BookInfo;
import com.example.kashite.domain.search.BookSearchService;
import com.example.kashite.framework.cqrs.CommandExecutor;

import java.util.UUID;

@Service
public class BookInfoService {
    @Autowired
    private BookSearchService bookSearchService;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private CommandExecutor executor;

    public String registerBookInfo(String id) {
        // Search API
        BookInfo bookInfo = bookSearchService.searchById(id);
        // Create Author
        for(String author : bookInfo.getAuthors()) {
            if(!authorDao.existsByAuthor(author)) {
                CreateAuthorCommand createAuthorCommand = new CreateAuthorCommand(UUID.randomUUID().toString(), author);
                executor.execute(createAuthorCommand);
            }
        }
        // Create BookInfo
        CreateBookInfoCommand createBookInfoCommand = new CreateBookInfoCommand(
                bookInfo.getId(),
                bookInfo.getIsbn(),
                bookInfo.getTitle(),
                bookInfo.getAuthors(),
                bookInfo.getPublisher(),
                bookInfo.getPublishedDate(),
                bookInfo.getDescription());
        executor.execute(createBookInfoCommand);
        return createBookInfoCommand.getId();
    }
}
