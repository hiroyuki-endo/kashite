package com.example.kashite.query.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BOOK")
public class BookEntity {
    @Id
    private String id;
    private String bookInfoId;
    private String lenderId;
    private String borrowerId;
    private String status;
}
