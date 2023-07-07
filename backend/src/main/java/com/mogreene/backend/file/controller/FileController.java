package com.mogreene.backend.file.controller;

import com.mogreene.backend.file.service.FileService;
import com.mogreene.backend.file.dto.FileDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

/**
 * @name : FileController
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 첨부파일 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     * 파일 다운로드
     * @param fileNo
     * @return
     * @throws MalformedURLException
     */
    @GetMapping("/download/{fileNo}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileNo) throws MalformedURLException {

        FileDTO fileDTO = fileService.downloadFile(fileNo);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, fileDTO.getContentDisposition())
                .body(fileDTO.getResource());
    }

    /**
     * 첨부파일 삭제
     * @param fileNo
     * @return
     */
    @DeleteMapping("/delete/{fileNo}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<?> deleteFile(@PathVariable Long fileNo,
                                        final Authentication authentication) {

        fileService.deleteFile(fileNo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
