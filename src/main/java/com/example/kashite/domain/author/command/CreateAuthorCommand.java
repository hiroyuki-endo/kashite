package com.example.kashite.domain.author.command;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateAuthorCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
