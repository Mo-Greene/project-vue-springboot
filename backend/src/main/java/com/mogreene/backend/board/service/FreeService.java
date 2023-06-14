package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;

import java.util.List;

/**
 * @name : FreeService
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 자유게시판 인터페이스
 */
public interface FreeService {

    //자유게시판 등록
    void postArticle(BoardDTO boardDto);

    //자유게시판 조회
    List<BoardDTO> getFreeArticle();

    //자유게시글 상세조회
    BoardDTO readFreeArticle(Long boardNo);
}
