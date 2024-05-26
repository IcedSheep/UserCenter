package com.sheep.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sheep.domain.User;
import com.sheep.domain.dto.UserRegisterRequest;

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
}
