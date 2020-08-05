package com.example.kashite.domain.book.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BorrowBookCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String id;
    private String borrowerId;
    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
