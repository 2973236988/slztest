package com.hedgeway.www.service.impl;


import com.hedgeway.www.dao.UserDao;
import com.hedgeway.www.dao.impl.UserDaoImpl;
import com.hedgeway.www.po.PageBean;
import com.hedgeway.www.po.User;
import com.hedgeway.www.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();
    /**
     * 登录方法
     */
    @Override
    public User login(User user) {
        User usr = null;
        try {
            usr =  dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr;
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try {
            users = dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        User u = new User();
        try {
            u = dao.findById(Integer.parseInt(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return u;
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao的方法删除
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows,Map<String, String[]> condition) throws SQLException {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }

}
