package com.example.kashite.domain.bookinfo.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookWroteEvent {
    private String bookInfoId;
    private String author;
}
