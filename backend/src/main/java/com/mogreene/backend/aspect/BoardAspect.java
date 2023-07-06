package com.mogreene.backend.aspect;

import com.mogreene.backend.board.dto.BoardDTO;
import com.mogreene.backend.user.dto.UserDTO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * 게시판 AOP 적용
 */
@Aspect
@Component
public class BoardAspect {

    /**
     * 작성자 토큰정보로 기입
     * @param boardDTO
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.board.controller.*.*(..)) && args(boardDTO, authentication)")
    public void setBoardWriter(BoardDTO boardDTO, Authentication authentication) {
        boardDTO.setBoardWriter(((UserDTO) authentication.getPrincipal()).getUsername());
    }
}
