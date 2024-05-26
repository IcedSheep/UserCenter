package com.sheep.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sheep
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据账号查询
     * @param userAccount 账号
     * @return
     */
    long selectByUserAccount(String userAccount);

    /**
     * 插入注册的用户信息
     * @param user
     * @return
     */
    Long insertUserInfo(User user);

    /**
     * 根据账号和密码查询用户
     * @param user
     * @return
     */
    User selectUserByAccountAndPsw(User user);
}




