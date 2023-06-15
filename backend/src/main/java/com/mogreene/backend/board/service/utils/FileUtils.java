package com.mogreene.backend.board.service.utils;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.board.dto.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @name : FileUtils
 * @author : Mo-Greene
 * @date : 2023/06/15
 * 첨부파일 인터페이스
 */
@Component
public class FileUtils {

    @Value("${mogreene.upload.path}")
    private String uploadPath;

    //첨부파일 등록
    public FileDTO saveAttachment(BoardDTO boardDTO, MultipartFile file) throws IOException {

        String fileOriginalName = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();
        assert fileOriginalName != null;
        String extension = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
        String saveFileName = uuid + "." + extension;

        String folderCategoryName = String.valueOf(boardDTO.getCategoryBoard());

        //폴더 생성
        String folderPath = makeFolder(folderCategoryName);

        String filePath = uploadPath + folderPath + saveFileName;

        return FileDTO.builder()
                .boardNo(boardDTO.getBoardNo())
                .fileOriginalName(fileOriginalName)
                .fileName(saveFileName)
                .filePath(filePath)
                .build();
    }

    //폴더별 저장 메서드
    private String makeFolder(String category) {

        String categoryFolder = category + "/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        String folderPath = categoryFolder.replace("/", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}
