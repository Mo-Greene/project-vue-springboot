package com.mogreene.backend.file.service;

import com.mogreene.backend.file.dto.FileDTO;

import java.net.MalformedURLException;

/**
 * @name : FileService
 * @author : Mo-Greene
 * @date : 2023/06/16
 * 첨부파일 인터페이스
 */
public interface FileService {

    //첨부파일 다운로드
    FileDTO downloadFile(Long fileNo) throws MalformedURLException;

    //파일 삭제
    void deleteFile(Long fileNo);
}
