package com.hedgeway.www.service.impl;


import com.hedgeway.www.dao.UserDao;
import com.hedgeway.www.dao.impl.UserDaoImpl;
import com.hedgeway.www.po.User;
import com.hedgeway.www.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();



    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User usr = null;
        try {
            usr =  userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr;
    }

}
