package com.mogreene.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @name : LoginDTO
 * @author : Mo-Greene
 * @date : 2023/07/05
 * 로그인 dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    //회원 username
    private String username;

    //비밀번호
    private String password;

}
