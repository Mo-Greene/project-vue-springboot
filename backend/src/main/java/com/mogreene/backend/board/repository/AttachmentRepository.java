package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @name : AttachmentRepository
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자료실 repository
 */
@Mapper
public interface AttachmentRepository {

    //자료실 등록
    void postAttachmentArticle(BoardDTO boardDTO);

    //자료실 조회
    List<BoardDTO> getAttachmentArticle();

    //자료실 상세조회
    BoardDTO readAttachment(Long boardNo);

    //자료실 수정
    void updateAttachmentArticle(BoardDTO boardDTO);
}
