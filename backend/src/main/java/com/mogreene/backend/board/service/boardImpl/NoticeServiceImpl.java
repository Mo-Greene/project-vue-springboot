package com.mogreene.backend.board.service.boardImpl;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.repository.BaseRepository;
import com.mogreene.backend.board.repository.NoticeRepository;
import com.mogreene.backend.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @name : NoticeServiceImpl
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 공지게시판 인터페이스 구현
 */
@Slf4j
@Service
@Qualifier("noticeService")
@RequiredArgsConstructor
public class NoticeServiceImpl implements BoardService {

    private final BaseRepository baseRepository;
    private final NoticeRepository noticeRepository;

    //공지게시판 등록
    @Override
    @Transactional
    public void postArticle(BoardDTO boardDto) {

        //base_board insert
        baseRepository.postBaseBoard(boardDto);

        //board_notice insert
        noticeRepository.postNoticeArticle(boardDto);
    }

    //공지게시판 조회
    @Override
    @Transactional(readOnly = true)
    public List<BoardDTO> getArticleList() {

        return noticeRepository.getNoticeArticle();
    }

    //공지게시판 상세조회
    @Override
    @Transactional(readOnly = true)
    public BoardDTO readArticle(Long boardNo) {

        //boardView +1
        baseRepository.updateBoardView(boardNo);
        return noticeRepository.readNoticeArticle(boardNo);
    }

    //공지게시글 수정
    @Override
    public void updateArticle(BoardDTO boardDTO) {

        //update table : board_notice
        noticeRepository.updateNoticeArticle(boardDTO);

        //update column : board_mod_date
        baseRepository.updateBaseBoard(boardDTO);
    }

    //공지게시글 삭제
    @Override
    public void deleteArticle(Long boardNo) {

        baseRepository.deleteBaseBoard(boardNo);
    }
}
