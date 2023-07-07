package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @name : AttachmentService
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자료실 인터페이스
 */
public interface AttachmentService {

    //자료실 등록 + 첨부파일
    void postAttachment(BoardDTO boardDTO, MultipartFile[] files) throws IOException;

    //자료실 조회
    List<BoardDTO> getAttachmentArticleList(PageRequestDTO pageRequestDTO);

    //페이지네이션
    PageResponseDTO pagination(PageRequestDTO pageRequestDTO);

    //자료실 상세조회
    BoardDTO readAttachmentArticle(Long boardNo);

    //자료실 수정페이지
    BoardDTO modifyAttachmentArticle(Long boardNo);

    //자료실 수정
    void updateAttachment(BoardDTO boardDTO, MultipartFile[] files) throws IOException;

    //자료실 수정(파일x)
    void updateAttachmentWithEmptyFiles(BoardDTO boardDTO);

    //게시글 삭제
    void deleteAttachmentArticle(Long boardNo);
}
