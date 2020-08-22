package com.example.kashite.query.model.bookauthor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BOOK_AUTHOR")
public class BookAuthorEntity {
    @Id
    private String id;
    private String bookInfoId;
    private String author;
}
