package com.mogreene.backend.user.service;

import com.mogreene.backend.user.dto.UserDTO;
import com.mogreene.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @name : CustomUserDetailService
 * @author : Mo-Greene
 * @date : 2023/07/05
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * pk로 db 검색 후 없을 경우 에러 리턴
     * @param userNo the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userNo) throws UsernameNotFoundException {
        return userRepository.findByUserNo(Long.valueOf(userNo))
                .map(this::addAuthorities)
                .orElseThrow(() -> new UsernameNotFoundException(userNo + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private UserDTO addAuthorities(UserDTO userDTO) {
        userDTO.setAuthorities(List.of(new SimpleGrantedAuthority(userDTO.getRole())));

        return userDTO;
    }
}
