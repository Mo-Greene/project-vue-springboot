package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDto;

import java.util.List;

public interface BoardService {

    List<BoardDto> readArticle();
}
