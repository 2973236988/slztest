package com.hedgeway.www.dao.impl;

import com.hedgeway.www.po.User;
import com.hedgeway.www.dao.UserDao;
import com.hedgeway.www.util.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {


    @Override
    public User findByUsername(String username) throws SQLException {
        User user = new User();
        ResultSet rs = null;
        try {
            //1.定义sql
            String sql = "select * from user where username = ?";
            rs = DBUtils.executeQuery(sql,username);
            //2.执行sql
        } catch (Exception e) {

        }
        while (rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUid(rs.getInt("uid"));
            user.setStudentId(rs.getString("studentid"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setCollege(rs.getString("college"));
            user.setCategory(rs.getString("category"));
        }

        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into user(username,password,email,studentid,gender,college,category) values(?,?,?,?,?,?,?)";
        //2.执行sql

        boolean flag = DBUtils.executeUpdate(sql,user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getStudentId(),
                user.getGender(),
                user.getCollege(),
                user.getCategory()
        );
    }


    /**
     * 根据用户名和密码查询的方法
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        ResultSet rs = null;
        User user = new User();
        try {
            //1.定义sql
            String sql = "select * from csdn_user where username = ? and password = ?";
            //2.执行sql
            rs = DBUtils.executeQuery(sql,username,password);
        } catch (Exception e) {

        }
        while (rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUid(rs.getInt("uid"));
            user.setStudentId(rs.getString("studentid"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setCollege(rs.getString("college"));
            user.setCategory(rs.getString("category"));
        }

        return user;
    }
}
