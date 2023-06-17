package com.mogreene.backend.board.dto.page;

import com.mogreene.backend.config.enumType.CategoryBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * @name : PageRequestDTO
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 페이지네이션 request
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {


    //현재 페이지
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    //페이지 당 보여주는 게시글
    @Builder.Default
    @Positive
    private int size = 10;

    //n 페이지 시작 index
    public int getSkip() {
        return (page - 1) * 10;
    }

    /* 검색조건 */
    //날짜 시작
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    //날짜 종료
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    //카테고리 검색
    private CategoryBoard categoryBoard;

    //검색어
    private String keyword;
}
