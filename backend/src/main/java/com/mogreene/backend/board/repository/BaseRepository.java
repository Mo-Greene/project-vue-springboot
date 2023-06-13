package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseRepository {

    List<BoardDto> getFreeArticle(String category);
}
