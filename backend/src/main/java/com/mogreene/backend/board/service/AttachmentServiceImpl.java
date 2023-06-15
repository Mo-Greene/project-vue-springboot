package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.FileDTO;
import com.mogreene.backend.board.repository.board.AttachmentRepository;
import com.mogreene.backend.board.repository.board.BaseRepository;
import com.mogreene.backend.board.repository.file.FileRepository;
import com.mogreene.backend.board.service.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @name : AttachmentServiceImpl
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 자료실 인터페이스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final FileUtils fileUtils;
    private final BaseRepository baseRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileRepository fileRepository;

    //자료실 게시글 + 첨부파일 등록
    @Override
    @Transactional
    public void postAttachment(BoardDTO boardDTO, List<MultipartFile> files) throws IOException {

        //base_board insert
        baseRepository.postBaseBoard(boardDTO);

        //board_attachment insert
        attachmentRepository.postAttachmentArticle(boardDTO);

        //attachment_file insert
        for (MultipartFile file : files) {

            //파일 존재하지 않을 경우 다음 파일
            if (file.isEmpty()) {
                continue;
            }

            //첨부파일 dto 생성
            FileDTO fileDTO = fileUtils.saveAttachment(boardDTO, file);

            //물리적 파일 저장
            file.transferTo(new File(fileDTO.getFilePath()));
            fileRepository.persistentFile(fileDTO);
        }
    }

    //자료실 조회
    @Override
    @Transactional(readOnly = true)
    public List<BoardDTO> getAttachmentArticleList() {

        return attachmentRepository.getAttachmentArticle();
    }

    //자료실 상세조회
    @Override
    public BoardDTO readAttachmentArticle(Long boardNo) {

        BoardDTO boardDTO = attachmentRepository.readAttachment(boardNo);
        boardDTO.setFileList(fileRepository.getFileList(boardNo));

        return boardDTO;
    }

    //게시글 삭제
    @Override
    public void deleteAttachmentArticle(Long boardNo) {

        baseRepository.deleteBaseBoard(boardNo);
    }
}
