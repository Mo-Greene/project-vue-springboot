package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;

public interface FreeService {

    void postArticle(BoardDTO boardDto);
}
