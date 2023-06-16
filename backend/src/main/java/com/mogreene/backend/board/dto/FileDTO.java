package com.mogreene.backend.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

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

    //파일 다운로드 리소스
    private Resource resource;

    //파일 헤더
    private String contentDisposition;
}
