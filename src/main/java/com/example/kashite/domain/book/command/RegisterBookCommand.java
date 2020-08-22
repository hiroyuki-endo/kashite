package com.example.kashite.domain.book.command;

import com.example.kashite.framework.cqrs.Command.Command;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class RegisterBookCommand implements Command {
    @TargetAggregateIdentifier
    @ApiModelProperty(hidden = true)
    private String id;
    private long version;
    private String bookInfoId;
    private String lenderId;

    public RegisterBookCommand() {
        this.id = newId();
    }
}
