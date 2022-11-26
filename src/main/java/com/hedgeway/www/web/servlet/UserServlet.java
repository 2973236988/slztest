package com.hedgeway.www.web.servlet;

import com.hedgeway.www.po.PageBean;
import com.hedgeway.www.po.User;
import com.hedgeway.www.service.UserService;
import com.hedgeway.www.service.impl.UserServiceImpl;
import com.hedgeway.www.util.Md5Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService service = new UserServiceImpl();

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */


    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取数据
        //2.1 获取用户填写的验证码
        String verifycode = request.getParameter("verifycode");

        //3.验证码校验
        //3.1 获取随机生成的验证码信息
        HttpSession session = request.getSession();
        String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");
        //确保验证码的一次性
        session.removeAttribute("CHECKCODE_SERVER");

        //判断
        if (!checkcode_server.equalsIgnoreCase(verifycode)){//验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误!");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;//验证码不正确，下面代码不需要执行了
        }
        //验证码正确

        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        //2.封装User对象
        User user = new User();
        String encode = null;

        try{
            BeanUtils.populate(user,map);
            try {
                encode = Md5Utils.encodeByMd5(user.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setPassword(encode);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Service查询方法
        User loginUser = service.login(user);
        System.out.println(loginUser);
        //6.判断是否登录成功
        if (loginUser != null){
            //登录成功
            //将用户存入session
            session.setAttribute("user",loginUser);
            //没有共享数据，重定向跳转页面
            response.sendRedirect(request.getContextPath()+"/login_ok.jsp");

        }else {
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误!");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    /**
     * 查询单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端

        writeValue(user,response);
    }
    public void userList(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //1.调用UserService完成查询
        UserServiceImpl service = new UserServiceImpl();
        List<User> users = service.findAll();
        //2.将List集合存入request域中
        request.setAttribute("users",users);
        //3.将list转发到list.jsp页面显示
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    public void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取map
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用Service方法修改用户信息
        UserServiceImpl service = new UserServiceImpl();
        service.updateUser(user);

        //5.跳转到查询所有用户信息的UserListServlet
        response.sendRedirect(request.getContextPath()+"/user/findUserByPage");
    }

    public void findUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //1.获取id
        String id =request.getParameter("id");
        //2.调用Service查询
        UserServiceImpl service = new UserServiceImpl();
        User user = service.findUserById(id);

        //3.将user存入request
        request.setAttribute("user",user);
        //优化：将请求头Referer，存入request，用户跳转返回
        request.setAttribute("Referer",request.getHeader("Referer"));

        //4.转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    public void findUserByPage(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");

        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }


        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();


        //2.调用service查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = null;
        try {
            pb = service.findUserByPage(currentPage,rows,condition);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //3.将PageBean存入request
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//将查询条件存入request
        //4.转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        Map<String, String[]> map = request.getParameterMap();
        //3.封装对象
        User user = new User();
        String encode = null;

        try{
            BeanUtils.populate(user,map);
            try {
                encode = Md5Utils.encodeByMd5(user.getStudentId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            user.setPassword(encode);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.调用Service保存用户
        UserServiceImpl service = new UserServiceImpl();
        service.addUser(user);

        //5.跳转到UserListServlet，显示出新添加的用户信息
        response.sendRedirect(request.getContextPath()+"/user/findUserByPage");
    }

    public void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取当前用户的id
        String id = request.getParameter("id");
        //3.调用Service删除方法
        UserServiceImpl service = new UserServiceImpl();
        service.deleteUser(id);
        //4.跳转到查询所有的UserListServlet
        response.sendRedirect(request.getContextPath()+"/user/findUserByPage");
    }

    public void delSelectedUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //1.获取选中的所有id
        String[] ids = request.getParameterValues("uid");
        //2.调用Service方法，删除id对应的用户
        UserServiceImpl service = new UserServiceImpl();
        service.delSelectedUser(ids);
        //3.跳转到查询所有的Servlet
        response.sendRedirect(request.getContextPath()+"/user/findUserByPage");
    }

    /**
     * 退出功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
