package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.service.FreeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FreeController {

    private final FreeService freeService;

    @GetMapping("/free")
    public ResponseEntity<?> readArticle() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/free")
    public ResponseEntity<?> postArticle(@RequestBody BoardDTO boardDto) {

        freeService.postArticle(boardDto);
        return null;
    }
}
