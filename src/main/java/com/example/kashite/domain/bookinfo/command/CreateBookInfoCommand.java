package com.example.kashite.domain.bookinfo.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBookInfoCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String id;
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
