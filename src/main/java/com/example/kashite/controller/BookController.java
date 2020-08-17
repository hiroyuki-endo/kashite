package com.example.kashite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kashite.domain.book.command.BorrowBookCommand;
import com.example.kashite.domain.book.command.DeregisterBookCommand;
import com.example.kashite.domain.book.command.RegisterBookCommand;
import com.example.kashite.domain.book.command.ReturnBookCommand;
import com.example.kashite.framework.cqrs.CommandExecutor;

@RestController
public class BookController {
    @Autowired
    private CommandExecutor executor;

    @PostMapping("books/register")
    @CrossOrigin
    public String registerBook(@RequestBody RegisterBookCommand cmd) {
        return executor.execute(cmd);
    }

    @PostMapping("books/borrow")
    @CrossOrigin
    public String borrowBook(@RequestBody BorrowBookCommand cmd) {
        return executor.execute(cmd);
    }

    @PostMapping("books/return")
    @CrossOrigin
    public String returnBook(@RequestBody ReturnBookCommand cmd) {
        return executor.execute(cmd);
    }

    @PostMapping("books/deregister")
    @CrossOrigin
    public String deregisterBook(@RequestBody DeregisterBookCommand cmd) {
        return executor.execute(cmd);
    }
}
