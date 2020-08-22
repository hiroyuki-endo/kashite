package com.example.kashite.domain.book.command;



import com.example.kashite.framework.cqrs.Command.AbstractCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReturnBookCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String id;
    private String borrowerId;
    @Override
    public String aggregateIdentifier() {
        return id;
    }
}
