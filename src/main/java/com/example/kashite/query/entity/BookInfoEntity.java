package com.example.kashite.query.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BOOK_INFO")
public class BookInfoEntity {
    @Id
    private String id;
    private String isbn;
    private String title;
    private String publisher;
    private String publishedDate;
    @Column(length = 2048)
    private String description;
    @Column(length = 2048)
    private String imageLink;
}
