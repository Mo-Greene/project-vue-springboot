package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDto;
import com.mogreene.backend.board.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BaseRepository baseRepository;

    @Override
    public List<BoardDto> readArticle() {

        String free = "FREE";
        String notice = "NOTICE";

        List<BoardDto> list = baseRepository.getFreeArticle(free);

        return list;
    }

    @Override
    public void postArticle(BoardDto boardDto) {

        log.info("boardDto : " + boardDto);
        baseRepository.postFreeArticle(boardDto);
    }
}
