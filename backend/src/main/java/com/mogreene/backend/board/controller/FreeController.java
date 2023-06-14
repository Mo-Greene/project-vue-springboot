package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.service.FreeService;
import com.mogreene.backend.config.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @name : FreeController
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 자유게시판 컨트롤러
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class FreeController {

    private final FreeService freeService;


    /**
     * 자유게시판 등록
     * @param boardDto
     * @return
     */
    @PostMapping("/free")
    public ResponseEntity<ApiResponseDTO<?>> postFreeArticle(@RequestBody BoardDTO boardDto) {

        freeService.postArticle(boardDto);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data("Created")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 자유게시판 조회
     * @return
     */
    @GetMapping("/free")
    public ResponseEntity<ApiResponseDTO<?>> getFreeArticle() {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(freeService.getFreeArticle())
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자유게시글 상세조회
     * @param boardNo
     * @return
     */
    @GetMapping("/free/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> readFreeArticle(@PathVariable Long boardNo) {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(freeService.readFreeArticle(boardNo))
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
}
