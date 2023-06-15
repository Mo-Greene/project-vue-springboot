package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;

import java.util.List;

/**
 * @name : BoardService
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 게시판 공용 인터페이스
 */
public interface BoardService {

    //게시판 등록
    void postArticle(BoardDTO boardDto);

    //게시판 조회
    List<BoardDTO> getArticleList();

    //게시글 상세조회
    BoardDTO readArticle(Long boardNo);

    //게시글 수정
    void updateArticle(BoardDTO boardDTO);

    //게시글 삭제
    void deleteArticle(Long boardNo);
}
