package com.hedgeway.www.util;

import java.sql.*;

/**
 * @Description:
 * @Class: DBUtils
 * @Package: cn.ithedge.csdn.util
 * @Author: hedgeway
 * @CreateTime: 2022/11/19 21:54
 */
public class DBUtils {

    public static Connection connection;

    static {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static PreparedStatement pstmt;
    public static ResultSet set;


    public static void createPreParedStatement(String sql,Object param) throws SQLException {
        pstmt =  connection.prepareStatement(sql);
        pstmt.setObject(1,param);
    }


    public static void createPreParedStatement(String sql,Object[] params) throws SQLException {
        pstmt =  connection.prepareStatement(sql);
        if(params!=null ) {
            for(int i=0;i<params.length;i++) {
                pstmt.setObject(i+1, params[i]);
            }
        }
    }

    public static ResultSet executeQuery(String sql) {
        try {
            pstmt =  connection.prepareStatement(sql);
            set = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static ResultSet executeQuery(String sql,Object param) {
        try {
            createPreParedStatement(sql,param);

            set = pstmt.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static ResultSet executeQuery(String sql,Object... params) {
        try {
            createPreParedStatement(sql,params);
            set = pstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static boolean executeUpdate(String sql,Object param){
        try {
            createPreParedStatement(sql,param);
            int flag =pstmt.executeUpdate();
            if(flag>0){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean executeUpdate(String sql,Object... params){
        try {
            createPreParedStatement(sql,params);
            int flag =pstmt.executeUpdate();
            if(flag>0){
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void close(ResultSet rs, Statement stmt, Connection connection)
    {
        try {
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs, Statement stmt)
    {
        try {
            if(rs!=null)
            {
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
