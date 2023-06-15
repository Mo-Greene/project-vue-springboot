package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.service.BoardService;
import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @name : NoticeController
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 공지게시판 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final BoardService noticeService;

    @Autowired
    public NoticeController(@Qualifier("noticeService") BoardService boardService) {
        this.noticeService = boardService;
    }

    /**
     * 공지게시판 등록
     * @param boardDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ApiResponseDTO<?>> postFreeArticle(@RequestBody BoardDTO boardDto) {

        noticeService.postArticle(boardDto);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data("Created")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 공지게시판 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<?>> getFreeArticle() {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(noticeService.getArticleList())
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 공지게시글 상세조회
     * @param boardNo
     * @return
     */
    @GetMapping("/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> readFreeArticle(@PathVariable Long boardNo) {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(noticeService.readArticle(boardNo))
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 공지게시글 수정
     * @param boardNo
     * @param boardDTO
     * @return
     */
    @PutMapping("/modify/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> updateFreeArticle(@PathVariable Long boardNo,
                                                               @RequestBody BoardDTO boardDTO) {

        boardDTO.setBoardNo(boardNo);
        noticeService.updateArticle(boardDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data("Modify_OK")
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 공지게시글 삭제
     * @param boardNo
     * @return
     */
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> deleteFreeArticle(@PathVariable Long boardNo) {

        noticeService.deleteArticle(boardNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
