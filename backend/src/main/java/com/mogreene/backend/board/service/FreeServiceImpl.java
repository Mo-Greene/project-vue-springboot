package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.repository.BaseRepository;
import com.mogreene.backend.board.repository.FreeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FreeServiceImpl implements FreeService {

    private final BaseRepository baseRepository;
    private final FreeRepository freeRepository;

    @Override
    @Transactional
    public void postArticle(BoardDTO boardDTO) {

        baseRepository.postBaseBoard(boardDTO);
        freeRepository.postFreeArticle(boardDTO);
    }
}
