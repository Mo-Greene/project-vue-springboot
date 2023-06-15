package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;

import java.util.List;

/**
 * @name : NoticeService
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 공지게시판 인터페이스
 */
public interface NoticeService {

    //게시판 등록
    void postNoticeArticle(BoardDTO boardDto);

    //게시판 조회
    List<BoardDTO> getNoticeArticleList();

    //게시글 상세조회
    BoardDTO readNoticeArticle(Long boardNo);

    //게시글 수정
    void updateNoticeArticle(BoardDTO boardDTO);

    //게시글 삭제
    void deleteNoticeArticle(Long boardNo);
}
