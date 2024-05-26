package com.sheep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheep.constants.UserInfoConstant;
import com.sheep.domain.User;
import com.sheep.domain.dto.UserLoginRequest;
import com.sheep.domain.dto.UserRegisterRequest;
import com.sheep.mapper.UserMapper;
import com.sheep.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Pattern;

/**
* @author sheep
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2024-05-25 22:51:52
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    /**
     * 用户注册
     * @param userRegisterRequest
     * @return 新用户的Id
     */
    @Override
    public long userRegister(UserRegisterRequest userRegisterRequest) {
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)) {
            // todo 封装全局响应
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        // 账号不能包含特殊字符
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(userAccount).matches()) {
            return -1;
        }
        if (userPassword.length() < 6 || checkPassword.length() < 6) {
            return -1;
        }
        // 密码和校验密码要一致
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 账号不能重复
        long count = userMapper.selectByUserAccount(userAccount);
        if (count > 0) {
            return -1;
        }
        // 对密码进行加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((UserInfoConstant.SALT + userPassword).getBytes());
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);
        Long id = userMapper.insertUserInfo(user);
        if (id < 0) {
            return -1;
        }
        return user.getId();
    }

    /**
     * 用户登录
     * @param userLoginRequest
     * @param request
     * @return
     */
    @Override
    public User userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)) {
            return null;
        }
        // 账号长度不少于6位
        if (userAccount.length() < 4) {
            return null;
        }
        // 账号不能包含特殊字符
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(userAccount).matches()) {
            return null;
        }
        // 密码不少于6位
        if (userPassword.length() < 6) {
            return null;
        }
        // 根据账号和密码校验用户是否存在
        String password = DigestUtils.md5DigestAsHex((UserInfoConstant.SALT + userPassword).getBytes());
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(password);
        user = userMapper.selectUserByAccountAndPsw(user);
        if (user == null) {
            return null;
        }
        // 用户信息脱敏
        User safetyUser = getSafetyUser(user);
        // 记录用户登录态
        request.getSession().setAttribute(UserInfoConstant.USER_LOGIN_STATUS,safetyUser);
        return safetyUser;
    }

    /**
     * 用户信息脱敏
     * @param orginalUser
     * @return
     */
    public User getSafetyUser(User orginalUser) {
        User user = new User();
        user.setId(orginalUser.getId());
        user.setUserAccount(orginalUser.getUserAccount());
        user.setUsername(orginalUser.getUsername());
        user.setAvatarUrl(orginalUser.getAvatarUrl());
        user.setGender(orginalUser.getGender());
        user.setEmail(orginalUser.getEmail());
        user.setProfile(orginalUser.getProfile());
        user.setUserRole(orginalUser.getUserRole());
        user.setPhone(orginalUser.getPhone());
        user.setCreateTime(orginalUser.getCreateTime());
        return user;

    }
}




