//package com.hedgeway.www.web.servlet;
//
//import com.hedgeway.www.po.PageBean;
//import com.hedgeway.www.po.User;
//import com.hedgeway.www.service.impl.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/findUserByPageServlet")
//public class findUserByPageServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String currentPage = request.getParameter("currentPage");
//        String rows = request.getParameter("rows");
//
//        UserServiceImpl service  = new UserServiceImpl();
//        PageBean<User> pb = service.findUserByPage(currentPage,rows);
//
//        request.setAttribute("pb", pb);
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
