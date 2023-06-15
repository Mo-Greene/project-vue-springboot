package com.mogreene.backend.board.repository.file;

import com.mogreene.backend.board.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @name : FileRepository
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 첨부파일 repository
 */
@Mapper
public interface FileRepository {

    void persistentFile(FileDTO fileDTO);

}
