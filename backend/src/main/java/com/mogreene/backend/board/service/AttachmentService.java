package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
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
    void postAttachment(BoardDTO boardDTO, List<MultipartFile> files) throws IOException;

}
