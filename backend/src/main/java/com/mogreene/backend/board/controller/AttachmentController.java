package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;
import com.mogreene.backend.board.service.AttachmentService;
import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @name : AttachmentController
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자료실 Controller
 */
@Slf4j
@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 자료실 등록
     * @param boardDTO
     * @param files
     */
    @PostMapping("")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> postAttachment(@RequestPart("boardDTO") BoardDTO boardDTO,
                                                            @RequestPart("file") MultipartFile[] files,
                                                            final Authentication authentication) throws IOException {

        attachmentService.postAttachment(boardDTO, files);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data("Created")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 게시글 수정
     * @param boardNo
     * @param boardDTO
     * @param files
     */
    @PutMapping("/modify/{boardNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> updateAttachment(@PathVariable Long boardNo,
                                                              @RequestPart("boardDTO") BoardDTO boardDTO,
                                                              @RequestPart(value = "file", required = false) MultipartFile[] files,
                                                              final Authentication authentication) throws IOException {

        if (files == null) {

            attachmentService.updateAttachmentWithEmptyFiles(boardDTO);
        } else {

            attachmentService.updateAttachment(boardDTO, files);
        }

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data("Modify_OK")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자료실 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ApiResponseDTO<?>> getAttachmentArticle(@Valid PageRequestDTO pageRequestDTO,
                                                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        List<BoardDTO> attachmentList = attachmentService.getAttachmentArticleList(pageRequestDTO);
        PageResponseDTO pagination = attachmentService.pagination(pageRequestDTO);

        Map<String, Object> attachmentObject = new HashMap<>();
        attachmentObject.put("attachmentList", attachmentList);
        attachmentObject.put("pagination", pagination);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(attachmentObject)
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자료실 상세조회
     * @param boardNo
     * @return
     */
    @GetMapping("/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> readAttachmentArticle(@PathVariable Long boardNo) {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(attachmentService.readAttachmentArticle(boardNo))
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 수정페이지 조회 (조회수 증가x)
     * @param boardNo
     * @return
     */
    @GetMapping("/modify/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> modifyAttachmentArticle(@PathVariable Long boardNo) {

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(attachmentService.modifyAttachmentArticle(boardNo))
                .build();

        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 자료실 게시글 삭제
     * @param boardNo
     * @return
     */
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> deleteAttachmentArticle(@PathVariable Long boardNo) {

        attachmentService.deleteAttachmentArticle(boardNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
