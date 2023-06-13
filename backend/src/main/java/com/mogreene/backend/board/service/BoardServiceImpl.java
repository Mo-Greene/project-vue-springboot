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

        List<BoardDto> list = baseRepository.getFreeArticle(notice);
        log.info("list : " + list);

        return list;
    }
}
