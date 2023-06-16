package com.mogreene.backend.reply.service;

import com.mogreene.backend.reply.dto.ReplyDTO;
import com.mogreene.backend.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @name : ReplyServiceImpl
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 댓글 구현
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    //댓글 등록
    @Override
    public void postReply(ReplyDTO replyDTO) {

        replyRepository.postReply(replyDTO);
    }

    //댓글 목록
    @Override
    public List<ReplyDTO> getReplyList(Long boardNo) {

        return replyRepository.getReplyList(boardNo);
    }

    //댓글 수정
    @Override
    public void updateReply(ReplyDTO replyDTO) {

        replyRepository.updateReply(replyDTO);
    }

    //댓글 비활성화
    @Override
    public void disableReply(Long replyNo) {

        replyRepository.disableReply(replyNo);
    }
}
