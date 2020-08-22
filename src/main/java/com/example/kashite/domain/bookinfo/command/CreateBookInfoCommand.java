package com.example.kashite.domain.bookinfo.command;

import com.example.kashite.framework.cqrs.Command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBookInfoCommand implements Command {
    @TargetAggregateIdentifier
    private String id;
    private long version;
    private String isbn;
    private String title;
    private String[] authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private String imageLink;

    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
