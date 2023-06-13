package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDto;
import com.mogreene.backend.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FreeController {

    private final BoardService boardService;

    @GetMapping("/free")
    public ResponseEntity<?> readArticle() {

        List<BoardDto> list = boardService.readArticle();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/free")
    public ResponseEntity<?> postArticle(@RequestBody BoardDto boardDto) {

        boardService.postArticle(boardDto);
        return null;
    }
}
