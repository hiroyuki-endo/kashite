package com.example.kashite.domain.book.event;

import com.example.kashite.domain.book.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDeregisteredEvent {
    private String id;
    private String lenderId;
    private BookStatus status;
}
