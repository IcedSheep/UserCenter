package com.sheep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheep.domain.User;
import com.sheep.domain.dto.UserLoginRequest;
import com.sheep.domain.dto.UserRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author sheep
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    long userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 用户登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    User userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);
}
