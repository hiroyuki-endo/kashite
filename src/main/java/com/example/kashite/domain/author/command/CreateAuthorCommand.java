package com.example.kashite.domain.author.command;

import com.example.kashite.framework.cqrs.Command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateAuthorCommand implements Command {
    @TargetAggregateIdentifier
    private String id;
    private long version;
    private String name;
    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
