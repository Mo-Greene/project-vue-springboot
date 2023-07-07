package com.mogreene.backend.aspect;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @name : BoardAspect
 * @author : Mo-Greene
 * @date : 2023/07/07
 * 게시판 AOP
 */
@Slf4j
@Aspect
@Component
public class BoardAspect {

    /**
     * 자유게시판 + 공지게시판 => 작성자, 토큰정보로 기입
     * @param boardDTO
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.board.controller.*.*(..)) && args(boardDTO, authentication)")
    public void setBoardWriter(BoardDTO boardDTO,
                               Authentication authentication) {

        //boardDto : 유저번호 + 유저이름 토큰내 정보로 기입
        boardDTO.setUserNo(((UserDTO) authentication.getPrincipal()).getUserNo());
        boardDTO.setBoardWriter(((UserDTO) authentication.getPrincipal()).getUsername());
    }

    /**
     * 자료실 => 작성자, 토큰정보로 기입
     * @param boardDTO
     * @param files
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.board.controller.AttachmentController.*(..)) && args(boardDTO, files, authentication)")
    public void setBoardWriter(BoardDTO boardDTO,
                               MultipartFile[] files,
                               Authentication authentication) {

        //boardDto : 유저번호 + 유저이름 토큰내 정보로 기입
        boardDTO.setUserNo(((UserDTO) authentication.getPrincipal()).getUserNo());
        boardDTO.setBoardWriter(((UserDTO) authentication.getPrincipal()).getUsername());
    }

    /**
     * 자유 + 공지 수정 => boardDto 설정
     * @param boardNo
     * @param boardDTO
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.board.controller.*.*(..)) && args(boardNo, boardDTO, authentication)")
    public void setModifyArticle(Long boardNo,
                                 BoardDTO boardDTO,
                                 Authentication authentication) {

        boardDTO.setBoardNo(boardNo);
        boardDTO.setBoardWriter(authentication.getName());
    }

    /**
     * 자료실 수정 => boardDto 설정
     * @param boardNo
     * @param boardDTO
     * @param files
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.board.controller.AttachmentController.*(..)) && args(boardNo, boardDTO, files, authentication)")
    public void setBoardWriter(Long boardNo,
                               BoardDTO boardDTO,
                               MultipartFile[] files,
                               Authentication authentication) {

        boardDTO.setBoardNo(boardNo);
        boardDTO.setBoardWriter(authentication.getName());
    }
}
