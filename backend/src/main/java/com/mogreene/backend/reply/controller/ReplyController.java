package com.mogreene.backend.reply.controller;

import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import com.mogreene.backend.reply.dto.ReplyDTO;
import com.mogreene.backend.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @name : ReplyController
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 댓글 controller
 */
@Slf4j
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 post
     * @param replyDTO
     * @return
     */
    @PostMapping("/{boardNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> postReply(@PathVariable Long boardNo,
                                                       @RequestBody ReplyDTO replyDTO,
                                                       final Authentication authentication) {

        replyService.postReply(replyDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data("Reply Created")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 대댓글 post
     * @param boardNo
     * @param replyDTO
     * @return
     */
    @PostMapping("/{boardNo}/{parentReplyNo}")
    public ResponseEntity<ApiResponseDTO<?>> postReComment(@PathVariable Long boardNo,
                                                           @PathVariable Long parentReplyNo,
                                                           @RequestBody ReplyDTO replyDTO) {

        replyDTO.setParentReplyNo(parentReplyNo);
        replyDTO.setBoardNo(boardNo);
        replyService.postReply(replyDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data("Re-Comment Created")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 댓글 목록
     * @param boardNo
     * @return
     */
    @GetMapping("/{boardNo}")
    public ResponseEntity<ApiResponseDTO<?>> getReplyList(@PathVariable Long boardNo) {

        List<ReplyDTO> replyDTOList =replyService.getReplyList(boardNo);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(replyDTOList)
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 댓글 수정
     * @param replyNo
     * @param replyDTO
     * @return
     */
    @PutMapping("/modify/{replyNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> updateReply(@PathVariable Long replyNo,
                                                         @RequestBody ReplyDTO replyDTO,
                                                         final Authentication authentication) {

        replyDTO.setReplyNo(replyNo);
        replyService.updateReply(replyDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data("Modify_OK")
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    /**
     * 댓글 비활성화
     * @param replyNo
     * @return
     */
    @PutMapping("/disable/{replyNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> disableReply(@PathVariable Long replyNo,
                                                          final Authentication authentication) {

        replyService.disableReply(replyNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
