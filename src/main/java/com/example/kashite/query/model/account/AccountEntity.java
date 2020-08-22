package com.example.kashite.query.model.account;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {
    @Id
    private String id;
    private String name;
    private String password;
    private int failedCount;
}
