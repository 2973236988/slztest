package com.hedgeway.www.service;

import java.util.List;
import java.util.Map;

import com.hedgeway.www.po.PageBean;
import com.hedgeway.www.po.User;

public interface UserService {

    /**
     * @description: 登录
     * @author: hedgeway
     * @CreateTime: 2022/11/20 18:01
     * @param: null
     * @return: null
     */
    User login(User user);

    /**
     * @description: 查询所有用户信息
     * @author: hedgeway
     * @CreateTime: 2022/11/20 18:01
     * @param: null
     * @return: null
     */
    List<User> findAll();

    //保存新添加的User
    void addUser(User user);

    //根据id删除User
    void deleteUser(String id);

    //根据id查询User
    User findUserById(String id);

    //修改用户信息
    void updateUser(User user);

    //批量删除用户
    void delSelectedUser(String[] ids);

    //分页查询
    //分页条件查询
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);
}
