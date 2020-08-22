package com.example.kashite.domain.book.command;

import com.example.kashite.framework.cqrs.Command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BorrowBookCommand implements Command {
    @TargetAggregateIdentifier
    private String id;
    private long version;
    private String borrowerId;
    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
