package com.hedgeway.www.dao;
import com.hedgeway.www.po.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * @description: 查询所有用户信息
     */
    public abstract List<User> findAll() throws SQLException;

    /**
     * 根据用户名查询用户信息

     */
    public User findByUsername(String username) throws SQLException;

    /**
     * 添加新的User
     */
    void add(User user);

    /**
     * @description: 用户名+密码进行登录
     */
    User findByUsernameAndPassword(String username, String password) throws SQLException;

    /**
     * 删除当前id对应的用户User
     */
    void delete(int id);

    /**
     * 根据当前id查询到用户User信息
     */
    User findById(int id) throws SQLException;

    /**
     * 修改用户User信息
     */
    void update(User user);

    /**
     * (条件)查询总记录数
     */
    int findTotalCount(Map<String, String[]> condition) throws SQLException;

    /**
     * 分页(条件)查询每页记录
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition) throws SQLException;
}
