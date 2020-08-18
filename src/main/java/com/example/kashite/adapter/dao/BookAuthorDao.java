package com.example.kashite.adapter.dao;

import com.example.kashite.query.entity.BookAuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorDao extends JpaRepository<BookAuthorEntity, String> {
    List<BookAuthorEntity> findByBookInfoId(String bookInfoId);
}
