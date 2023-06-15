package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;

import java.util.List;

/**
 * @name : FreeService
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자유 게시판 인터페이스
 */
public interface FreeService {

    //게시판 등록
    void postFreeArticle(BoardDTO boardDto);

    //게시판 조회
    List<BoardDTO> getFreeArticleList();

    //게시글 상세조회
    BoardDTO readFreeArticle(Long boardNo);

    //게시글 수정
    void updateFreeArticle(BoardDTO boardDTO);

    //게시글 삭제
    void deleteFreeArticle(Long boardNo);
}
