package com.sheep.domain.dto;

import lombok.Data;

/**
 * 用户注册包装类
 */
@Data
public class UserRegisterRequest {
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 校验密码
     */
    private String checkPassword;
}
