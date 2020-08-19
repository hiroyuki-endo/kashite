package com.example.kashite.query.service;

import com.example.kashite.adapter.dao.AuthorDao;
import com.example.kashite.adapter.dao.BookAuthorDao;
import com.example.kashite.adapter.dao.BookInfoDao;
import com.example.kashite.query.dto.BookInfoDto;
import com.example.kashite.query.entity.BookAuthorEntity;
import com.example.kashite.query.entity.BookInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class BookInfoQuery {

    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private BookAuthorDao bookAuthorDao;

    public List<BookInfoDto>  findAllBookInfo() {
        Map<String, List<String>> bookAuthorDic = bookAuthorDao.findAll().stream()
                .collect(groupingBy(BookAuthorEntity::getBookInfoId, mapping(BookAuthorEntity::getAuthor, toList())));

        return bookInfoDao.findAll().stream()
                .map(entity -> convertFrom(entity,
                        bookAuthorDic.get(entity.getId()).toArray(new String[bookAuthorDic.size()]))).collect(toList());
    }

    public BookInfoDto findOneBookInfo(String id) {
        List<String> authors = bookAuthorDao.findByBookInfoId(id).stream()
                .map(BookAuthorEntity::getId)
                .collect(Collectors.toList());

        Optional<BookInfoEntity> bookInfoEntity = bookInfoDao.findById(id);
        return bookInfoEntity.map(e -> convertFrom(e,authors.toArray(new String[authors.size()]))).orElse(null);
    }

    private BookInfoDto convertFrom(BookInfoEntity entity, String[] authors) {
        return new BookInfoDto(
                entity.getId(),
                entity.getIsbn(),
                entity.getTitle(),
                0,
                0,
                authors,
                entity.getPublisher(),
                entity.getPublishedDate(),
                entity.getDescription(),
                entity.getImageLink());
    }
}
