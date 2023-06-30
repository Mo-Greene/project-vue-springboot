package com.mogreene.backend.board.service;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.config.enumType.CategoryBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class FreeServiceTest {

    @Autowired
    private FreeService freeService;

    @Test
    void postFreeArticle() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            BoardDTO boardDTO = BoardDTO.builder()
                    .boardTitle("Free Title.." + i)
                    .boardContent("Content.." + i)
                    .boardWriter("Tester " + (i % 10))
                    .categoryBoard(CategoryBoard.FREE)
                    .build();

            freeService.postFreeArticle(boardDTO);
        });
    }
}
