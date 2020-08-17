package com.example.kashite.domain.book.event;

import com.example.kashite.domain.book.BookStatus;
import com.example.kashite.domain.book.command.RegisterBookCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookRegisteredEvent {
    private String id;
    private String bookInfoId;
    private String lenderId;
    private BookStatus status;

    public static BookRegisteredEvent from(RegisterBookCommand cmd) {
        return new BookRegisteredEvent(
                cmd.getId(),
                cmd.getBookInfoId(),
                cmd.getLenderId(),
                BookStatus.READY
        );
    }
}
