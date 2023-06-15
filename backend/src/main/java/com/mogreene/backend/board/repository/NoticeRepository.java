package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @name : NoticeRepository
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 공지게시판 repository
 */
@Mapper
public interface NoticeRepository {

    //공지게시판 등록
    void postNoticeArticle(BoardDTO boardDTO);

    //공지게시판 조회
    List<BoardDTO> getNoticeArticle();

    //공지게시글 상세조회
    BoardDTO readNoticeArticle(Long boardNo);

    //게시글 수정
    void updateNoticeArticle(BoardDTO boardDTO);
}
