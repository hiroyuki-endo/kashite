package com.example.kashite.controller;

import java.util.List;

import com.example.kashite.adapter.dao.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.kashite.adapter.dao.BookInfoDao;
import com.example.kashite.controller.request.CreateBookInfoRequest;
import com.example.kashite.query.entity.BookInfoEntity;
import com.example.kashite.service.BookInfoService;

@RestController
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private BookInfoDao bookInfoDao;

    @GetMapping("bookinfos")
    @CrossOrigin
    public List<BookInfoEntity> bookInfos() {
        return bookInfoDao.findAll();
    }

    @GetMapping("bookinfos/{id}")
    @CrossOrigin
    public BookInfoEntity bookInfo(@PathVariable String id) {
        return bookInfoDao.getOne(id);
    }

    @DeleteMapping("bookinfos/{id}")
    @CrossOrigin
    public void deleteBookInfo(@PathVariable String id) {
        bookInfoDao.deleteById(id);
    }

    @PostMapping("bookinfos")
    @CrossOrigin
    public String createBookInfo(@RequestBody CreateBookInfoRequest cmd) {
        return bookInfoService.registerBookInfo(cmd.getId());
    }
}
