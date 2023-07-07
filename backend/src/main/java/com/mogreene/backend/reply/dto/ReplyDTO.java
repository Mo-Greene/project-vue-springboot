package com.mogreene.backend.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @name : ReplyDTO
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 댓글 dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    //댓글 번호
    private Long replyNo;

    //게시글 번호
    private Long boardNo;

    //댓글 내용
    private String replyContent;

    //댓글 작성자
    private String replyWriter;

    //댓글 작성시간
    private LocalDateTime replyRegDate;

    //부모 댓글 번호
    private Long parentReplyNo;

    //유저 번호(fk)
    private Long userNo;
}
