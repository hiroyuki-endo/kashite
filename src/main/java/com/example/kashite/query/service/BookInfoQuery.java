package com.example.kashite.query.service;

import com.example.kashite.query.dto.BookInfoDto;
import com.example.kashite.query.model.bookauthor.BookAuthorEntity;
import com.example.kashite.query.model.bookauthor.BookAuthorRepository;
import com.example.kashite.query.model.bookinfo.BookInfoEntity;
import com.example.kashite.query.model.bookinfo.BookInfoRepository;
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
    private BookInfoRepository bookInfoRepository;

    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    public List<BookInfoDto>  findAllBookInfo() {
        Map<String, List<String>> bookAuthorDic = bookAuthorRepository.findAll().stream()
                .collect(groupingBy(BookAuthorEntity::getBookInfoId, mapping(BookAuthorEntity::getAuthor, toList())));

        return bookInfoRepository.findAll().stream()
                .map(entity -> convertFrom(entity,
                        bookAuthorDic.get(entity.getId()).toArray(new String[bookAuthorDic.size()]))).collect(toList());
    }

    public BookInfoDto findOneBookInfo(String id) {
        List<String> authors = bookAuthorRepository.findByBookInfoId(id).stream()
                .map(BookAuthorEntity::getId)
                .collect(Collectors.toList());

        Optional<BookInfoEntity> bookInfoEntity = bookInfoRepository.findById(id);
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
