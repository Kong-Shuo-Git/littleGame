package com.littleGame.service.impl;

import com.littleGame.dao.UserDao;
import com.littleGame.pojo.User;
import com.littleGame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户数据访问层
     */
    @Autowired
    private UserDao userDao;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // 登录成功，更新最后登录时间
            updateLastLoginTime(user.getUserId());
            return user;
        }
        return null;
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 是否注册成功
     */
    @Override
    public boolean register(String username, String password, String nickname) {
        // 检查用户名是否已存在
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        // 创建新用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname != null && !nickname.isEmpty() ? nickname : username);
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        return userDao.insert(user) > 0;
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户对象
     */
    @Override
    public User getUserInfo(Integer userId) {
        return userDao.findById(userId);
    }

    /**
     * 更新用户最后登录时间
     * @param userId 用户ID
     */
    @Override
    public void updateLastLoginTime(Integer userId) {
        User user = userDao.findById(userId);
        if (user != null) {
            user.setLastLoginTime(new Date());
            userDao.update(user);
        }
    }
}