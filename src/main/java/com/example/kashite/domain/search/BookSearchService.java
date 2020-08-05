package com.example.kashite.domain.search;

import java.util.List;


public interface BookSearchService {

    List<BookInfo> search(String intitle, String author);

    BookInfo searchById(String id);
}
