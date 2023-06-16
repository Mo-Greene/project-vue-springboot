package com.mogreene.backend.reply.service;

import com.mogreene.backend.reply.dto.ReplyDTO;

import java.util.List;

/**
 * @name : ReplyService
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 댓글 인터페이스
 */
public interface ReplyService {

    //댓글 등록
    void postReply(ReplyDTO replyDTO);

    //댓글 목록
    List<ReplyDTO> getReplyList(Long boardNo);

    //댓글 수정
    void updateReply(ReplyDTO replyDTO);

    //댓글 비활성화
    void disableReply(Long replyNo);
}
