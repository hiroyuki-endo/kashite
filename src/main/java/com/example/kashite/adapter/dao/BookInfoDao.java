package com.example.kashite.adapter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kashite.query.entity.BookInfoEntity;

@Repository
public interface BookInfoDao extends JpaRepository<BookInfoEntity, String> {
}
