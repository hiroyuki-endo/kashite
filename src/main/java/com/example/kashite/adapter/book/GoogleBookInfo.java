package com.example.kashite.adapter.book;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class GoogleBookInfo {
    private String title;
    private String[] authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private List<GoogleIsbn> industryIdentifiers;
    private int pageCount;
    private String language;
    private String previewLink;
    private String infoLink;
    private String canonicalVolumeLink;
    private GoogleImageLink imageLinks;

    public String getIsbn() {
        if(Objects.isNull(industryIdentifiers)) return null;
        return industryIdentifiers.stream()
                .filter(isbn -> isbn.getType().equals("ISBN_13"))
                .map(GoogleIsbn::getIdentifier)
                .findFirst().orElse(null);
    }
}
