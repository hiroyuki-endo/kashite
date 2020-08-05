package com.example.kashite.framework.cqrs;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.kashite.framework.cqrs.Command.Command;

@Component
public class CommandExecutor {

    @Autowired
    private CommandGateway commandGateway;

    public <R> R execute(Command cmd) {
        return commandGateway.sendAndWait(cmd);
    }
}
