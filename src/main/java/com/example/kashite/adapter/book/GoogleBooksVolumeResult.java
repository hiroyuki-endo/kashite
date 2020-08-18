package com.example.kashite.adapter.book;

import java.util.Map;

import com.example.kashite.domain.search.BookInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleBooksVolumeResult implements BookInfo {
    private String kind;
    private String id;
    private String etag;
    private String selfLink;
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

    @Override
    public String getImageLink() {
        return volumeInfo.getImageLinks().getThumbnail();
    }
}
