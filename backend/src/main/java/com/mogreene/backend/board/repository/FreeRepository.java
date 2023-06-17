package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @name : FreeRepository
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 자유게시판 repository
 */
@Mapper
public interface FreeRepository {

    //자유게시판 등록
    void postFreeArticle(BoardDTO boardDto);

    //자유게시판 조회
    List<BoardDTO> getFreeArticle(PageRequestDTO pageRequestDTO);

    //게시글 갯수
    int totalFreeCount(PageRequestDTO pageRequestDTO);

    //자유게시글 상세조회
    BoardDTO readFreeArticle(Long boardNo);

    //자유게시글 수정
    void updateFreeArticle(BoardDTO boardDTO);
}
