package com.mogreene.backend.board.service.boardImpl;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.repository.BaseRepository;
import com.mogreene.backend.board.repository.FreeRepository;
import com.mogreene.backend.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("freeService")
@RequiredArgsConstructor
public class FreeServiceImpl implements BoardService {

    private final BaseRepository baseRepository;
    private final FreeRepository freeRepository;

    //자유게시판 등록
    @Override
    @Transactional
    public void postArticle(BoardDTO boardDTO) {

        //base_board insert
        baseRepository.postBaseBoard(boardDTO);

        //board_free insert
        freeRepository.postFreeArticle(boardDTO);
    }

    //자유게시판 조회
    @Override
    @Transactional(readOnly = true)
    public List<BoardDTO> getArticleList() {

        return freeRepository.getFreeArticle();
    }

    //자유게시글 상세조회
    @Override
    @Transactional(readOnly = true)
    public BoardDTO readArticle(Long boardNo) {

        //조회수 증가
        baseRepository.updateBoardView(boardNo);

        return freeRepository.readFreeArticle(boardNo);
    }

    //자유게시글 수정
    @Override
    public void updateArticle(BoardDTO boardDTO) {

        //update board_free table
        freeRepository.updateFreeArticle(boardDTO);

        //update column : board_mod_date
        baseRepository.updateBaseBoard(boardDTO);
    }

    //자유게시글 삭제
    @Override
    public void deleteArticle(Long boardNo) {

        baseRepository.deleteBaseBoard(boardNo);
    }
}
