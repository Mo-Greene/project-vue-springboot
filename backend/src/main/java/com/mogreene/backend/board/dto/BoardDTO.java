package com.mogreene.backend.board.dto;

import com.mogreene.backend.config.enumType.CategoryBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @name : BoardDTO
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 게시판 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    //게시글 번호
    private Long boardNo;

    //게시글 제목
    private String boardTitle;

    //게시글 작성자
    private String boardWriter;

    //게시글 조회수
    private int boardView;

    //게시글 내용
    private String boardContent;

    //게시글 생성일시
    private LocalDateTime boardRegDate;

    //게시글 수정일시
    private LocalDateTime boardModDate;

    //카테고리 분류
    private CategoryBoard categoryBoard;

}
