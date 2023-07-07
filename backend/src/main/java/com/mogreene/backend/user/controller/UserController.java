package com.mogreene.backend.user.controller;

import com.mogreene.backend.config.responseApi.ApiResponseDTO;
import com.mogreene.backend.jwt.JwtFilter;
import com.mogreene.backend.jwt.TokenProvider;
import com.mogreene.backend.user.dto.LoginDTO;
import com.mogreene.backend.user.dto.UserDTO;
import com.mogreene.backend.user.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @name : UserController
 * @author : Mo-Greene
 * @date : 2023/07/05
 * 회원 로직
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입 로직
     * @param userDTO
     * @return
     * @throws DuplicateMemberException
     */
    // TODO: 2023/07/05 예외처리 뽑아주자
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO<?>> signUp(@RequestBody UserDTO userDTO) throws DuplicateMemberException {

        UserDTO savedUser = userService.signUp(userDTO);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.CREATED)
                .data(savedUser)
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.CREATED);
    }

    /**
     * 로그인 token response
     * @param loginDTO
     * @return
     * @throws LoginException
     */
    // TODO: 2023/07/05 예외처리 뽑아주자
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) throws LoginException {

        String token = userService.login(loginDTO);
        UserDTO userDTO = userService.findByUsername(loginDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", userDTO);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(response);
    }

    /**
     * 테스트용 유저 조회
     * @param authentication
     * @return
     * @throws UserPrincipalNotFoundException
     */
    // TODO: 2023/07/05 예외처리 뽑아주자
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ApiResponseDTO<?>> findUserByUsername(final Authentication authentication) throws UserPrincipalNotFoundException {
        Long userNo = ((UserDTO) authentication.getPrincipal()).getUserNo();

        UserDTO findUser = userService.findByUserNo(userNo);

        ApiResponseDTO<?> apiResponseDTO = ApiResponseDTO.builder()
                .httpStatus(HttpStatus.OK)
                .data(findUser)
                .build();
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
}
