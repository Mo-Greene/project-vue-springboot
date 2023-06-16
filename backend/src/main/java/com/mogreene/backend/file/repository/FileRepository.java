package com.mogreene.backend.file.repository;

import com.mogreene.backend.file.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @name : FileRepository
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 첨부파일 repository
 */
@Mapper
public interface FileRepository {

    //파일 저장
    void persistentFile(FileDTO fileDTO);

    //파일 조회
    List<FileDTO> getFileList(Long boardNo);

    //단일 첨부파일 조회
    FileDTO getSingleFile(Long fileNo);

    //첨부파일 삭제
    void deleteFile(Long fileNo);
}
