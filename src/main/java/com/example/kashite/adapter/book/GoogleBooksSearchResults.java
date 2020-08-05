package com.example.kashite.adapter.book;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleBooksSearchResults {
    private String kind;
    private int totalItems;
    private List<GoogleBooksVolumeResult> items;
}
