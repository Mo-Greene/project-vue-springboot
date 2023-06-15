package com.mogreene.backend.board.controller;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.service.AttachmentService;
import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<ApiResponseDTO<?>> postAttachment(@RequestPart("boardDTO") BoardDTO boardDTO,
                                                            @RequestPart("file") List<MultipartFile> files) throws IOException {

        attachmentService.postAttachment(boardDTO, files);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data("Created")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }
}
