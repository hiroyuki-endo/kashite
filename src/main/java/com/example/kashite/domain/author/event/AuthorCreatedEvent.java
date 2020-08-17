package com.example.kashite.domain.author.event;

import com.example.kashite.domain.author.command.CreateAuthorCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorCreatedEvent {
    private String id;
    private String name;

    public static AuthorCreatedEvent from(CreateAuthorCommand cmd) {
        return new AuthorCreatedEvent(cmd.getId(), cmd.getName());
    }
}
