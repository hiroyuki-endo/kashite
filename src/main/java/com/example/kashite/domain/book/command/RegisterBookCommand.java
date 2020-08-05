package com.example.kashite.domain.book.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterBookCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    @ApiModelProperty(hidden = true)
    private String id;
    private String bookInfoId;
    private String lenderId;

    public RegisterBookCommand() {
        this.id = newId();
    }

    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
