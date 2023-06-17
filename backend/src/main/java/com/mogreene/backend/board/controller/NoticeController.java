package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;
import com.mogreene.backend.board.service.NoticeService;
import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name : NoticeController
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 공지게시판 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 공지게시판 등록
     * @param boardDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ApiResponseDTO<?>> postFreeArticle(@RequestBody BoardDTO boardDto) {

        noticeService.postNoticeArticle(boardDto);

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
    public ResponseEntity<ApiResponseDTO<?>> getFreeArticle(@Valid PageRequestDTO pageRequestDTO,
                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        List<BoardDTO> noticeList = noticeService.getNoticeArticleList(pageRequestDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(noticeList)
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
                .data(noticeService.readNoticeArticle(boardNo))
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
        noticeService.updateNoticeArticle(boardDTO);

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

        noticeService.deleteNoticeArticle(boardNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
