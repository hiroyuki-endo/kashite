package com.example.kashite.domain.bookinfo.event;

import com.example.kashite.domain.bookinfo.command.CreateBookInfoCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookInfoCreatedEvent {
    private String id;
    private String isbn;
    private String title;
    private String[] authors;
    private String publisher;
    private String publishedDate;
    private String description;

    public static BookInfoCreatedEvent fromCommnad(CreateBookInfoCommand cmd) {
        return new BookInfoCreatedEvent(
                cmd.getId(),
                cmd.getIsbn(),
                cmd.getTitle(),
                cmd.getAuthors(),
                cmd.getPublisher(),
                cmd.getPublishedDate(),
                cmd.getDescription()
        );
    }
}
