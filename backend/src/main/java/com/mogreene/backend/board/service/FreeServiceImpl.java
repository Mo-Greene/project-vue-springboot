package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.repository.BaseRepository;
import com.mogreene.backend.board.repository.FreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @name : FreeServiceImpl
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 자유게시판 인터페이스 구현
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FreeServiceImpl implements FreeService {

    private final BaseRepository baseRepository;
    private final FreeRepository freeRepository;

    //자유게시판 등록
    @Override
    @Transactional
    public void postArticle(BoardDTO boardDTO) {

        baseRepository.postBaseBoard(boardDTO);
        freeRepository.postFreeArticle(boardDTO);
    }

    //자유게시판 조회
    @Override
    public List<BoardDTO> getFreeArticle() {

        return freeRepository.getFreeArticle();
    }

    //자유게시글 상세조회
    @Override
    public BoardDTO readFreeArticle(Long boardNo) {

        return freeRepository.readFreeArticle(boardNo);
    }

    //자유게시글 수정
    @Override
    public void updateFreeArticle(BoardDTO boardDTO) {

        freeRepository.updateFreeArticle(boardDTO);
        baseRepository.updateBaseBoard(boardDTO);
    }

    //자유게시글 삭제
    @Override
    public void deleteFreeArticle(Long boardNo) {

        baseRepository.deleteBaseBoard(boardNo);
    }
}
