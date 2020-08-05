package com.example.kashite.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kashite.adapter.dao.BookInfoDao;
import com.example.kashite.api.request.CreateBookInfoRequest;
import com.example.kashite.query.entity.BookInfoEntity;
import com.example.kashite.service.BookInfoService;

@RestController
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private BookInfoDao bookInfoDao;

    @GetMapping("bookinfos")
    public List<BookInfoEntity> bookInfos() {
        return bookInfoDao.findAll();
    }

    @GetMapping("bookinfos/{id}")
    public BookInfoEntity bookInfo(@PathVariable String id) {
        return bookInfoDao.getOne(id);
    }

    @DeleteMapping("bookinfos/{id}")
    public void deleteBookInfo(@PathVariable String id) {
        bookInfoDao.deleteById(id);
    }

    @PostMapping("bookinfos")
    public String createBookInfo(@RequestBody CreateBookInfoRequest cmd) {
        return bookInfoService.registerBookInfo(cmd.getId());
    }
}
