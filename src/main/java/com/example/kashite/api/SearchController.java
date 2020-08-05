package com.example.kashite.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kashite.domain.search.BookInfo;
import com.example.kashite.domain.search.BookSearchService;

@RestController
public class SearchController {

    @Autowired
    private BookSearchService bookSearchService;

    @GetMapping("bookinfos/search")
    @CrossOrigin
    public List<BookInfo> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
        return bookSearchService.search(title, author);
    }
}
