package com.example.kashite.domain.reader.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReaderCreatedEvent {
    private String id;
    private String name;
}
