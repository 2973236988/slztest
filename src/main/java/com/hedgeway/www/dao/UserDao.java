package com.hedgeway.www.dao;
import com.hedgeway.www.po.User;

import java.sql.SQLException;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username) throws SQLException;

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);


    User findByUsernameAndPassword(String username, String password) throws SQLException;
}
