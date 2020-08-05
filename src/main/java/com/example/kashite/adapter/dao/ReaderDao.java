package com.example.kashite.adapter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kashite.query.entity.ReaderEntity;

@Repository
public interface ReaderDao extends JpaRepository<ReaderEntity, String> {
}
