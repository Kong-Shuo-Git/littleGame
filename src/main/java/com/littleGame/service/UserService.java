package com.littleGame.service;

import com.littleGame.pojo.User;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    User login(String username, String password);

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 是否注册成功
     */
    boolean register(String username, String password, String nickname);

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户对象
     */
    User getUserInfo(Integer userId);

    /**
     * 更新用户最后登录时间
     *
     * @param userId 用户ID
     */
    void updateLastLoginTime(Integer userId);
}