package com.example.backend.controller;

import com.example.backend.entity.Board;
import com.example.backend.entity.Comment;
import com.example.backend.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping("/register/{boardNo}")
    public void commentRegister(@PathVariable("boardNo") Integer boardNo, @Validated @RequestBody Comment comment) {
        log.info("commentRegister()");

        service.register(boardNo, comment);
    }

    @GetMapping("/list/{boardNo}")
    public List<Comment> commentList(@PathVariable("boardNo") Integer boardNo) {
        log.info("commentList()");

        return service.list(boardNo);
    }

    @GetMapping("/twoList/{boardNo}")
    public List<Comment> twoCommentList(@PathVariable("boardNo") Integer boardNo) {
        log.info("commentList()");

        return service.twoList(boardNo);
    }

    @PutMapping("/{commentNo}")
    public Comment commentModify (
            @PathVariable("commentNo") Integer commentNo,
            @RequestBody Comment comment) {
        log.info("boardModify(): " + comment);

        comment.setCommentNo(Long.valueOf(commentNo));
        service.modify(comment);

        return comment;
    }

    @DeleteMapping("/{commentNo}")
    public void commentRemove (@PathVariable("commentNo") Integer commentNo) {
        log.info("commentRemove()");

        service.remove(commentNo);
    }
}
