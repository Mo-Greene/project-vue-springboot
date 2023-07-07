package com.mogreene.backend.aspect;

import com.mogreene.backend.reply.dto.ReplyDTO;
import com.mogreene.backend.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @name : ReplyAspect
 * @author : Mo-Greene
 * @date : 2023/07/07
 * 댓글 AOP
 */
@Slf4j
@Component
@Aspect
public class ReplyAspect {

    /**
     * 댓글 등록 aop
     * @param boardNo
     * @param replyDTO
     * @param authentication
     */
    @Before("execution(* com.mogreene.backend.reply.controller.*.*(..)) && args(boardNo, replyDTO, authentication)")
    public void setBoardNo(Long boardNo,
                           ReplyDTO replyDTO,
                           Authentication authentication) {

        replyDTO.setBoardNo(boardNo);
        replyDTO.setReplyWriter(authentication.getName());
        replyDTO.setUserNo(((UserDTO) authentication.getPrincipal()).getUserNo());
    }
}
