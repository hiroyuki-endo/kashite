package com.example.kashite.domain.account.command;

import com.example.kashite.framework.cqrs.Command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteAccountCommand implements Command {

    private String id;
    private long version;
}
