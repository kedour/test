package com.itmybatis.dao;

import com.itmybatis.domain.QueryVo;
import com.itmybatis.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     */

    void  saveUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 根据ia删除用户
     * @param userId
     */
    void  deleteUser(Integer userId);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
     User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
     List<User> findByName(String username);

    /**
     * 查询用户总记录
     * @return
     */
     int findTotal();

    /**
     * 根据Queryvo的条件查询
     * @param vo
     * @return
     */
     List<User> findUserByVo(QueryVo vo);
}