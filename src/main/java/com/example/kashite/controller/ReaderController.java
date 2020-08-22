package com.example.kashite.controller;

import java.util.List;

import com.example.kashite.query.model.reader.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kashite.domain.reader.command.CreateReaderCommand;
import com.example.kashite.framework.cqrs.CommandExecutor;
import com.example.kashite.query.model.reader.ReaderEntity;

@RestController
public class ReaderController {
    @Autowired
    private CommandExecutor executor;
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping("readers")
    public List<ReaderEntity> readers() {
        return readerRepository.findAll();
    }

    @GetMapping("readers/{readerId}")
    public ReaderEntity reader(@PathVariable String readerId) {
        return readerRepository.getOne(readerId);
    }

    @PostMapping("readers")
    public Object createReader(@RequestBody CreateReaderCommand cmd) {
        return executor.execute(cmd);
    }
}
