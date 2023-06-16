package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * @name : AttachmentService
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자료실 인터페이스
 */
public interface AttachmentService {

    //자료실 등록 + 첨부파일
    void postAttachment(BoardDTO boardDTO, List<MultipartFile> files) throws IOException;

    //자료실 조회
    List<BoardDTO> getAttachmentArticleList();

    //자료실 상세조회
    BoardDTO readAttachmentArticle(Long boardNo);

    //게시글 삭제
    void deleteAttachmentArticle(Long boardNo);

    //첨부파일 다운로드
    FileDTO downloadFile(Long fileNo) throws MalformedURLException;
}
