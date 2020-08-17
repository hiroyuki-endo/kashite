package com.example.kashite.adapter.dao;

import com.example.kashite.query.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<AuthorEntity, String> {

    boolean existsByAuthor(String author);
}
