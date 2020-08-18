package com.example.kashite.domain.search;

public interface BookInfo {
    String getId();
    String getIsbn();
    String getTitle();
    String[] getAuthors();
    String getPublisher();
    String getPublishedDate();
    String getDescription();
    String getImageLink();

}
