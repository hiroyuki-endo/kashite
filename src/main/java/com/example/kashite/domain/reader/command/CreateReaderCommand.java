package com.example.kashite.domain.reader.command;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

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
