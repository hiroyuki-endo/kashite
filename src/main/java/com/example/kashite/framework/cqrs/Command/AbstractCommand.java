package com.example.kashite.framework.cqrs.Command;

import lombok.Getter;

@Getter
public abstract class AbstractCommand implements Command {
    long version;

    @Override
    public long aggreateVersion() {
        return version;
    }
}
