package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;
import com.mogreene.backend.board.service.FreeService;
import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name : FreeController
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 자유게시판 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/free")
@RequiredArgsConstructor
public class FreeController {

    private final FreeService freeService;

    /**
     * 자유게시판 등록
     * @param boardDto
     * @return
     */
    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> postFreeArticle(@RequestBody BoardDTO boardDto,
                                                             final Authentication authentication) {

        freeService.postFreeArticle(boardDto);

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
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<?>> getFreeArticle(@Valid PageRequestDTO pageRequestDTO,
                                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        List<BoardDTO> freeList = freeService.getFreeArticleList(pageRequestDTO);
        PageResponseDTO pagination = freeService.pagination(pageRequestDTO);

        Map<String, Object> freeObject = new HashMap<>();
        freeObject.put("freeList", freeList);
        freeObject.put("pagination", pagination);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(freeObject)
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자유게시글 상세조회
     * @param boardNo
     * @return
     */
    @GetMapping("/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> readFreeArticle(@PathVariable Long boardNo) {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(freeService.readFreeArticle(boardNo))
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 수정페이지 게시글 조회(조회수 증가 x)
     * @param boardNo
     * @return
     */
    @GetMapping("/modify/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> modifyFreeArticle(@PathVariable Long boardNo) {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(freeService.modifyFreeArticle(boardNo))
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자유게시글 수정
     * @param boardDTO
     * @return
     */
    @PutMapping("/modify/{boardNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> updateFreeArticle(@PathVariable Long boardNo,
                                                               @RequestBody BoardDTO boardDTO,
                                                               final Authentication authentication) {

        boardDTO.setBoardNo(boardNo);
        boardDTO.setBoardWriter(authentication.getName());
        freeService.updateFreeArticle(boardDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data("Modify_OK")
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자유게시글 삭제
     * @param boardNo
     * @return
     */
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> deleteFreeArticle(@PathVariable Long boardNo) {

        freeService.deleteFreeArticle(boardNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
