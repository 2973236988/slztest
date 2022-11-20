package com.hedgeway.www.po;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {
    //用户id
    private int uid;
    //用户名，账号
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //学号
    private String studentId;
    //性别
    private String gender;
    //学院
    private String college;
    //组别
    private String category;


    /**
     * 无参构造方法
     */
    public User() {
    }

    public User(String username, String password, String email, String studentId, String gender, String college, String category) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.studentId = studentId;
        this.gender = gender;
        this.college = college;
        this.category = category;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", studentId='" + studentId + '\'' +
                ", gender='" + gender + '\'' +
                ", college='" + college + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
