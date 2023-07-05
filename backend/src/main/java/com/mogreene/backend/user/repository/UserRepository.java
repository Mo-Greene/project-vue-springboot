package com.mogreene.backend.user.repository;

import com.mogreene.backend.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * @name : UserRepository
 * @author : Mo-Greene
 * @date : 2023/07/05
 * UserRepository
 */
@Mapper
public interface UserRepository {

    Optional<UserDTO> findUserByUsername(String username);

    Optional<UserDTO> findByUserNo(Long userNo);

    void signUp(UserDTO userDTO);
}
