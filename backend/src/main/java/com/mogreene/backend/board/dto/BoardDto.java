package com.mogreene.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long boardNo;
    private String boardTitle;
    private String boardWriter;
    private int boardView;
    private String boardContent;
    private Timestamp boardRegDate;
    private Timestamp boardModDate;
    private CategoryBoard categoryBoard;
}
