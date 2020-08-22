package com.example.kashite.query.model.bookauthor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, String> {
    List<BookAuthorEntity> findByBookInfoId(String bookInfoId);
}
