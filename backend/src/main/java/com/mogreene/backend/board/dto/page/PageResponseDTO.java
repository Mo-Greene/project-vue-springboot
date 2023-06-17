package com.mogreene.backend.board.dto.page;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @name : PageResponseDTO
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 페이지네이션 response
 */
@Getter
@ToString
public class PageResponseDTO {

    //현재 페이지
    private int page;

    //페이지 당 게시글 수
    private int size;

    //검색된 데이터 개수
    private int total;

    //시작 페이지
    private int startPage;

    //끝 페이지
    private int endPage;

    //이전 존재 여부
    private boolean prev;

    //다음 존재 여부
    private boolean next;

    @Builder(builderMethodName = "pagination")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.endPage = (int)(Math.ceil((double) this.page / 10)) * 10;
        this.startPage = endPage - 9;
        /* 총 게시글이 endPage 보다 적을 경우 */
        int last = (int)(Math.ceil((total / (double) size)));
        this.endPage = Math.min(endPage, last);
        this.prev = this.startPage > 1;
        this.next = total > this.endPage * this.size;
    }
}
