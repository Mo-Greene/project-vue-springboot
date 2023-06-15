package com.mogreene.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @name : FileDTO
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 첨부파일 Dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    //첨부파일 번호
    private Long fileNo;

    //첨부파일 암호화 이름
    private String fileName;

    //첨부파일 실제 이름
    private String fileOriginalName;

    //첨부파일 경로
    private String filePath;

    //게시글 번호 fk
    private Long boardNo;

}
