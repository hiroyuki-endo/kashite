package com.example.kashite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import com.example.kashite.domain.search.BookInfo;
import com.example.kashite.domain.search.BookSearchService;
import com.example.kashite.framework.cqrs.CommandExecutor;

@Service
public class BookInfoService {
    @Autowired
    private BookSearchService bookSearchService;
    @Autowired
    private CommandExecutor executor;

    public String registerBookInfo(String id) {
        BookInfo bookInfo = bookSearchService.searchById(id);
        CreateBookInfoCommand cmd = new CreateBookInfoCommand(
                bookInfo.getId(),
                bookInfo.getIsbn(),
                bookInfo.getTitle(),
                bookInfo.getAuthors(),
                bookInfo.getPublisher(),
                bookInfo.getPublishedDate(),
                bookInfo.getDescription());
        executor.execute(cmd);
        return cmd.getId();
    }
}
