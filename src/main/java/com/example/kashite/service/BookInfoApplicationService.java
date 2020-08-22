package com.example.kashite.service;

import com.example.kashite.domain.author.command.CreateAuthorCommand;
import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import com.example.kashite.domain.search.BookInfo;
import com.example.kashite.domain.search.BookSearchService;
import com.example.kashite.framework.cqrs.CommandExecutor;
import com.example.kashite.query.model.author.AuthorRepository;
import com.example.kashite.query.model.bookauthor.BookAuthorEntity;
import com.example.kashite.query.model.bookauthor.BookAuthorRepository;
import com.example.kashite.query.model.bookinfo.BookInfoRepository;
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
    private AuthorRepository authorRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private BookInfoRepository bookInfoRepository;
    @Autowired
    private CommandExecutor executor;

    public String registerBookInfo(String id) {
        // Search API
        BookInfo bookInfo = bookSearchService.searchById(id);
        // Create Author
        for(String author : bookInfo.getAuthors()) {
            if(!authorRepository.existsByAuthor(author)) {
                CreateAuthorCommand createAuthorCommand = new CreateAuthorCommand(UUID.randomUUID().toString(), 0, author);
                executor.execute(createAuthorCommand);
            }
        }
        // Create BookInfo
        CreateBookInfoCommand createBookInfoCommand = new CreateBookInfoCommand(
                bookInfo.getId(),
                0,
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
        List<BookAuthorEntity> authors = bookAuthorRepository.findByBookInfoId(id).stream()
                .collect(Collectors.toList());
        bookAuthorRepository.deleteAll(authors);
        bookInfoRepository.deleteById(id);
    }
}
