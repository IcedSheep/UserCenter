package com.sheep.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录包装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    private String userAccount;
    private String userPassword;
}
