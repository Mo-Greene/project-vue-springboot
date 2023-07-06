package com.mogreene.backend.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @name : JwtAccessDeniedHandler
 * @author : Mo-Greene
 * @date : 2023/07/05
 * 403에러
 */
@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 필요한 권한이 없이 접근시 403에러
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.error("403(Access Denied) error");

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "필요한 권한이 없는 상태에서 접근하여 오류가 발생하였습니다.");
    }
}
