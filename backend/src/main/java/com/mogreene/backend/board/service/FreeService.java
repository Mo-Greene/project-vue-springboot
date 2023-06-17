package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;

import java.util.List;

/**
 * @name : FreeService
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자유 게시판 인터페이스
 */
public interface FreeService {

    //페이지네이션
    PageResponseDTO pagination(PageRequestDTO pageRequestDTO);

    //게시판 등록
    void postFreeArticle(BoardDTO boardDto);

    //게시판 조회
    List<BoardDTO> getFreeArticleList(PageRequestDTO pageRequestDTO);

    //게시글 상세조회
    BoardDTO readFreeArticle(Long boardNo);

    //게시글 수정
    void updateFreeArticle(BoardDTO boardDTO);

    //게시글 삭제
    void deleteFreeArticle(Long boardNo);
}
