package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @name : BaseRepository
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 부모게시판 repository
 */
@Mapper
public interface BaseRepository {

    //게시글 개수
    int totalCount(PageRequestDTO pageRequestDTO);

    //게시판 등록
    void postBaseBoard(BoardDTO boardDTO);

    //게시글 수정
    void updateBaseBoard(BoardDTO boardDTO);

    //게시글 삭제
    void deleteBaseBoard(Long boardNo);

    //게시글 조회수 증가
    void updateBoardView(Long boardNo);
}
