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
 * 게시판 AOP 적용
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
    public void setBoardWriter(BoardDTO boardDTO, Authentication authentication) {

        boardDTO.setBoardWriter(((UserDTO) authentication.getPrincipal()).getUsername());
    }

    /**
     * 자료실 => 작성자, 토큰정보로 기입
     * @param boardDTO
     * @param files
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.board.controller.AttachmentController.*(..)) && args(boardDTO, files, authentication)")
    public void setBoardWriter(BoardDTO boardDTO, MultipartFile[] files, Authentication authentication) {

        boardDTO.setBoardWriter(((UserDTO) authentication.getPrincipal()).getUsername());
    }
}
