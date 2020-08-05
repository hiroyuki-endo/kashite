package com.example.kashite.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kashite.adapter.dao.ReaderDao;
import com.example.kashite.domain.reader.command.CreateReaderCommand;
import com.example.kashite.framework.cqrs.CommandExecutor;
import com.example.kashite.query.entity.ReaderEntity;

@RestController
public class ReaderController {
    @Autowired
    private CommandExecutor executor;
    @Autowired
    private ReaderDao readerDao;

    @GetMapping("readers")
    public List<ReaderEntity> readers() {
        return readerDao.findAll();
    }

    @GetMapping("readers/{readerId}")
    public ReaderEntity reader(@PathVariable String readerId) {
        return readerDao.getOne(readerId);
    }

    @PostMapping("readers")
    public Object createReader(@RequestBody CreateReaderCommand cmd) {
        return executor.execute(cmd);
    }
}
