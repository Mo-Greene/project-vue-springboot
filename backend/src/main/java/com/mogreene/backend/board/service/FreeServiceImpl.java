package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.page.PageRequestDTO;
import com.mogreene.backend.board.dto.page.PageResponseDTO;
import com.mogreene.backend.board.repository.BaseRepository;
import com.mogreene.backend.board.repository.FreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @name : BoardServiceImpl
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
    public void postFreeArticle(BoardDTO boardDTO) {

        //base_board insert
        baseRepository.postBaseBoard(boardDTO);

        //board_free insert
        freeRepository.postFreeArticle(boardDTO);
    }

    //자유게시판 조회
    @Override
    @Transactional(readOnly = true)
    public List<BoardDTO> getFreeArticleList(PageRequestDTO pageRequestDTO) {

        return freeRepository.getFreeArticle(pageRequestDTO);
    }

    //자유게시판 페이지네이션
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO pagination(PageRequestDTO pageRequestDTO) {

        int total = freeRepository.totalFreeCount(pageRequestDTO);

        return PageResponseDTO.pagination()
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .build();
    }

    //자유게시글 상세조회
    @Override
    @Transactional(readOnly = true)
    public BoardDTO readFreeArticle(Long boardNo) {

        //조회수 증가
        baseRepository.updateBoardView(boardNo);

        return freeRepository.readFreeArticle(boardNo);
    }

    //수정페이지 상세조회(조회수 증가 x)
    @Override
    @Transactional(readOnly = true)
    public BoardDTO modifyFreeArticle(Long boardNo) {

        return freeRepository.readFreeArticle(boardNo);
    }

    //자유게시글 수정
    @Override
    public void updateFreeArticle(BoardDTO boardDTO) {

        //update board_free table
        freeRepository.updateFreeArticle(boardDTO);

        //update column : board_mod_date
        baseRepository.updateBaseBoard(boardDTO);
    }

    //자유게시글 삭제
    @Override
    public void deleteFreeArticle(Long boardNo) {

        baseRepository.deleteBaseBoard(boardNo);
    }
}
