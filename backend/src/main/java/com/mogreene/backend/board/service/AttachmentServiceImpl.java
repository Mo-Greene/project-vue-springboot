package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;
import com.mogreene.backend.board.repository.AttachmentRepository;
import com.mogreene.backend.board.repository.BaseRepository;
import com.mogreene.backend.file.dto.FileDTO;
import com.mogreene.backend.file.repository.FileRepository;
import com.mogreene.backend.file.service.utils.FileUtils;
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
    public void postAttachment(BoardDTO boardDTO, MultipartFile[] files) throws IOException {

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
    public List<BoardDTO> getAttachmentArticleList(PageRequestDTO pageRequestDTO) {

        return attachmentRepository.getAttachmentArticle(pageRequestDTO);
    }

    //자료실 페이지네이션
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO pagination(PageRequestDTO pageRequestDTO) {

        int total = attachmentRepository.totalAttachmentCount(pageRequestDTO);

        return PageResponseDTO.pagination()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .build();
    }

    //자료실 상세조회
    @Override
    @Transactional(readOnly = true)
    public BoardDTO readAttachmentArticle(Long boardNo) {

        //게시글 조회수 증가
        baseRepository.updateBoardView(boardNo);

        BoardDTO boardDTO = attachmentRepository.readAttachment(boardNo);
        boardDTO.setFileList(fileRepository.getFileList(boardNo));

        return boardDTO;
    }

    //게시글 수정페이지 (조회수 증가x)
    @Override
    @Transactional(readOnly = true)
    public BoardDTO modifyAttachmentArticle(Long boardNo) {

        BoardDTO boardDTO = attachmentRepository.readAttachment(boardNo);
        boardDTO.setFileList(fileRepository.getFileList(boardNo));

        return boardDTO;
    }

    //게시글 수정(파일 존재시)
    @Override
    public void updateAttachment(BoardDTO boardDTO, MultipartFile[] files) throws IOException {

        //base_board 수정시간 update
        baseRepository.updateBaseBoard(boardDTO);

        //board_attachment update
        attachmentRepository.updateAttachmentArticle(boardDTO);

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

    //게시글 수정 (파일x)
    @Override
    public void updateAttachmentWithEmptyFiles(BoardDTO boardDTO) {

        //base_board 수정시간 update
        baseRepository.updateBaseBoard(boardDTO);

        //board_attachment update
        attachmentRepository.updateAttachmentArticle(boardDTO);
    }

    //게시글 삭제
    @Override
    public void deleteAttachmentArticle(Long boardNo) {

        baseRepository.deleteBaseBoard(boardNo);
    }
}
