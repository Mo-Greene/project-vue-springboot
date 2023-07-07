package com.mogreene.backend.file.service;

import com.mogreene.backend.file.repository.FileRepository;
import com.mogreene.backend.file.dto.FileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

/**
 * @name : FileServiceImpl
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 첨부파일 구현
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    //첨부파일 다운로드
    @Override
    public FileDTO downloadFile(Long fileNo) throws MalformedURLException {

        FileDTO fileDTO = fileRepository.getSingleFile(fileNo);
        UrlResource resource = new UrlResource("file:" + fileDTO.getFilePath());
        String encodeName = UriUtils.encode(fileDTO.getFileOriginalName(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeName + "\"";

        fileDTO.setResource(resource);
        fileDTO.setContentDisposition(contentDisposition);

        return fileDTO;
    }

    //첨부파일 삭제
    @Override
    @Transactional
    public void deleteFile(Long fileNo) {

        //삭제할 첨부파일 조회
        FileDTO fileDTO = fileRepository.getSingleFile(fileNo);

        //첨부파일 삭제
        File file = new File(fileDTO.getFilePath());
        fileRepository.deleteFile(fileNo);
        boolean isFileDeleted = file.delete();

        if (!isFileDeleted) {
            throw new RuntimeException("파일삭제 오류");
        }
    }
}
