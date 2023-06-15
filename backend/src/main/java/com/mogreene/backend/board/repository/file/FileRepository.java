package com.mogreene.backend.board.repository.file;

import com.mogreene.backend.board.dto.FileDTO;
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
}
