package com.example.kashite.service;

import com.example.kashite.adapter.dao.AuthorDao;
import com.example.kashite.adapter.dao.BookAuthorDao;
import com.example.kashite.adapter.dao.BookInfoDao;
import com.example.kashite.domain.author.command.CreateAuthorCommand;
import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import com.example.kashite.domain.search.BookInfo;
import com.example.kashite.domain.search.BookSearchService;
import com.example.kashite.framework.cqrs.CommandExecutor;
import com.example.kashite.query.entity.BookAuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookInfoApplicationService {
    @Autowired
    private BookSearchService bookSearchService;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookAuthorDao bookAuthorDao;
    @Autowired
    private BookInfoDao bookInfoDao;
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
                bookInfo.getDescription(),
                bookInfo.getImageLink());
        executor.execute(createBookInfoCommand);
        return createBookInfoCommand.getId();
    }

    public void deleteFor(String id) {
        List<BookAuthorEntity> authors = bookAuthorDao.findByBookInfoId(id).stream()
                .collect(Collectors.toList());
        bookAuthorDao.deleteAll(authors);
        bookInfoDao.deleteById(id);
    }
}
