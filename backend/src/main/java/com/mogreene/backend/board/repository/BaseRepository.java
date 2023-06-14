package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @name : BaseRepository
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 부모 게시판
 */
@Mapper
public interface BaseRepository {

    //게시판 등록
    void postBaseBoard(BoardDTO boardDTO);

    //게시글 수정
    void updateBaseBoard(BoardDTO boardDTO);

    //게시글 삭제
    void deleteBaseBoard(Long boardNo);
}
