package com.example.kashite.adapter.book;

import com.example.kashite.domain.search.BookInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class GoogleBooksSearchResult implements BookInfo {
    private String id;
    private GoogleBookInfo volumeInfo;
    private GoogleSaleInfo saleInfo;
    private Map<String, Object> accessInfo;
    private Map<String, Object> searchInfo;

    @Override
    public String getIsbn() {
        return volumeInfo.getIsbn();
    }

    @Override
    public String getTitle() {
        return volumeInfo.getTitle();
    }

    @Override
    public String[] getAuthors() {
        return volumeInfo.getAuthors();
    }

    @Override
    public String getPublisher() {
        return volumeInfo.getPublisher();
    }

    @Override
    public String getPublishedDate() {
        return volumeInfo.getPublishedDate();
    }

    @Override
    public String getDescription() {
        return volumeInfo.getDescription();
    }
}
