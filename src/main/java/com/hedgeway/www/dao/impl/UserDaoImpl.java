package com.hedgeway.www.dao.impl;

import com.hedgeway.www.po.User;
import com.hedgeway.www.dao.UserDao;
import com.hedgeway.www.util.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<User>();
        ResultSet rs = null;
        //1.定义sql
        String sql = "select * from user";
        //2.使用JdbcTemplate方法，执行sql
        rs = DBUtils.executeQuery(sql);

        User u = new User();
        while (rs.next()){
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setUid(rs.getInt("uid"));
            u.setStudentId(rs.getString("studentId"));
            u.setGender(rs.getString("gender"));
            u.setEmail(rs.getString("email"));
            u.setCollege(rs.getString("college"));
            u.setCategory(rs.getString("category"));
            users.add(u);
            u = new User();
        }

        return users;
    }

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
            e.printStackTrace();
        }
        while (rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUid(rs.getInt("uid"));
            user.setStudentId(rs.getString("studentId"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setCollege(rs.getString("college"));
            user.setCategory(rs.getString("category"));
        }
        return user;
    }

    @Override
    public void add(User user) {
            //1.定义sql
            String sql = "insert into user(username,password,email,studentId,gender,college,category) values(?,?,?,?,?,?,?)";
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
            String sql = "select * from user where username = ? and password = ?";
            //2.执行sql
            rs = DBUtils.executeQuery(sql,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUid(rs.getInt("uid"));
            user.setStudentId(rs.getString("studentId"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setCollege(rs.getString("college"));
            user.setCategory(rs.getString("category"));
        }
        return user;
    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql = "delete from user where id = ?";
        //2.执行sql
        boolean flag = DBUtils.executeUpdate(sql, id);
    }

    @Override
    public User findById(int id) throws SQLException {
        //1.定义sql
        String sql = "select * from user where id = ?";
        //2.执行sql
        ResultSet rs = DBUtils.executeQuery(sql, id);
        User user = new User();
        while (rs.next()){
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setUid(rs.getInt("uid"));
            user.setStudentId(rs.getString("studentId"));
            user.setGender(rs.getString("gender"));
            user.setEmail(rs.getString("email"));
            user.setCollege(rs.getString("college"));
            user.setCategory(rs.getString("category"));
        }
        return user;
    }

    @Override
    public void update(User user) {
        //1.定义sql
        String sql = "update user set username = ?,password = ?,email = ?,studentId = ?,gender = ?,college = ? ,category = ? where id = ?";
        //2.执行sql
        DBUtils.executeUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getStudentId(), user.getGender(), user.getCollege(),user.getCategory(), user.getUid());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) throws SQLException {
        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";//注意空格，需要符合sql语法
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义sql参数的集合
        List<Object> params = new ArrayList<Object>();

        for (String key : keySet) {
            //复杂条件查询优化：排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                //跳过本次循环
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                //复杂查询优化：注意模糊查询语法
                params.add("%"+value+"%");//添加 ？ 条件的值
            }
        }
        //拼接后完整的sql语句
        sql = sb.toString();

        ResultSet rs = DBUtils.executeQuery(sql, params.toArray());
        int count = 0;
        while (rs.next()) {
            count++;
        }

        return count;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) throws SQLException {
        /*
         * 分页查询
         * 第一个?:开始查询记录的索引
         * 第二个?:查询的记录数
         * */
        //String sql = "select * from user limit ?,?";

        //1.定义模板初始化sql
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义sql参数的集合
        List<Object> params = new ArrayList<Object>();

        for (String key : keySet) {

            //复杂条件查询优化：排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                //跳过本次循环
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                //复杂查询优化：注意模糊查询语法
                params.add("%"+value+"%");//添加 ？ 条件的值
            }
        }
        //添加分页的查询语法
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);

        //拼接后完整的sql语句
        sql = sb.toString();

        ResultSet rs = DBUtils.executeQuery(sql, params);
        List<User> users  = new ArrayList<User>();
        User u = new User();
        while (rs.next()){
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setUid(rs.getInt("uid"));
            u.setStudentId(rs.getString("studentId"));
            u.setGender(rs.getString("gender"));
            u.setEmail(rs.getString("email"));
            u.setCollege(rs.getString("college"));
            u.setCategory(rs.getString("category"));
            users.add(u);
            u = new User();
        }

        return users;


    }
}
