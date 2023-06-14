package com.mogreene.backend.board.repository;

import com.mogreene.backend.board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FreeRepository {

    void postFreeArticle(BoardDTO boardDto);

}
