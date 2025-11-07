package com.littleGame.dao;

import com.littleGame.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据访问接口
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(String username);

    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return 用户对象
     */
    User findById(Integer userId);

    /**
     * 插入用户
     * @param user 用户对象
     * @return 受影响的行数
     */
    int insert(User user);

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 受影响的行数
     */
    int update(User user);
}