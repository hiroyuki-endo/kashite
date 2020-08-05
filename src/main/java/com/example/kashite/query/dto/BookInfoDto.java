package com.example.kashite.query.dto;

import com.example.kashite.domain.search.BookInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoDto implements BookInfo {
    private String id;
    private String isbn;
    private String title;
    private int page;
    private int amount;
    private String[] authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private String imageLink;
}
