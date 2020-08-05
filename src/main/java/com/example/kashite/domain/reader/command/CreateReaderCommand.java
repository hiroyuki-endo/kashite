package com.example.kashite.domain.reader.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateReaderCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;

    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
