package com.mogreene.backend.user.service;

import com.mogreene.backend.jwt.TokenProvider;
import com.mogreene.backend.user.dto.LoginDTO;
import com.mogreene.backend.user.dto.UserDTO;
import com.mogreene.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;

/**
 * @name : UserService
 * @author : Mo-Greene
 * @date : 2023/07/05
 * 회원 로직
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입 로직
     * @param userDTO
     * @return
     */
    public UserDTO signUp(UserDTO userDTO) throws DuplicateMemberException {
        if (userRepository.findUserByUsername(userDTO.getUsername()).isPresent()) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.signUp(userDTO);

        return userRepository.findUserByUsername(userDTO.getUsername()).get();
    }

    /**
     * 로그인 로직
     * @param loginDTO
     * @return
     * @throws LoginException
     */
    public String login(LoginDTO loginDTO) throws LoginException {
        UserDTO userDTO = userRepository.findUserByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new LoginException("잘못된 아이디 입니다."));

        if (!passwordEncoder.matches(loginDTO.getPassword(), userDTO.getPassword())) {
            throw new LoginException("잘못된 비밀번호입니다.");
        }

        return tokenProvider.createToken(userDTO.getUserNo(), Collections.singletonList(userDTO.getRole()));
    }

    /**
     * 유저 조회
     * @param userNo
     * @return
     * @throws UserPrincipalNotFoundException
     */
    public UserDTO findByUserNo(Long userNo) throws UserPrincipalNotFoundException {
        return userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new UserPrincipalNotFoundException("없는 유저입니다."));
    }
}
