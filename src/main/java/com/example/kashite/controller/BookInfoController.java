package com.example.kashite.controller;

import com.example.kashite.adapter.dao.BookInfoDao;
import com.example.kashite.controller.request.CreateBookInfoRequest;
import com.example.kashite.query.dto.BookInfoDto;
import com.example.kashite.query.service.BookInfoQuery;
import com.example.kashite.service.BookInfoApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookInfoController {
    @Autowired
    private BookInfoApplicationService bookInfoApplicationService;
    @Autowired
    private BookInfoDao bookInfoDao;
    @Autowired
    private BookInfoQuery bookInfoQuery;

    @GetMapping("bookinfos")
    @CrossOrigin
    public List<BookInfoDto> bookInfos() {
        return bookInfoQuery.findAllBookInfo();
    }

    @GetMapping("bookinfos/{id}")
    @CrossOrigin
    public BookInfoDto bookInfo(@PathVariable String id) {
        return bookInfoQuery.findOneBookInfo(id);
    }

    @DeleteMapping("bookinfos/{id}")
    @CrossOrigin
    public void deleteBookInfo(@PathVariable String id) {
        bookInfoApplicationService.deleteFor(id);
    }

    @PostMapping("bookinfos")
    @CrossOrigin
    public String createBookInfo(@RequestBody CreateBookInfoRequest cmd) {
        return bookInfoApplicationService.registerBookInfo(cmd.getId());
    }
}
