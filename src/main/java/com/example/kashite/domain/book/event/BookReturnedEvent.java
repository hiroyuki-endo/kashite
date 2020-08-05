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
public class BookReturnedEvent {
    private String id;
    private String bookInfoId;
    private String borrowerId;
    private BookStatus status;
}
