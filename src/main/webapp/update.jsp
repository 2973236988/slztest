<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script type="text/javascript">
        function back() {
            //返回查询所有用户信息的UserListServlet
            location.href = "${Referer}";
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/user/updateUser" method="post">

        <%--设置一个隐藏域，提交需要传递用户的id属性--%>
        <input type="hidden" name="id" value="${user.uid}">

        <div class="form-group">
            <label for="username">姓名：</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.username}" readonly="readonly" <%--readonly仅可读，不能修改--%> placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <%--需要判断数据库中用户性别信息，使用JSTL标签--%>
            <c:if test="${user.gender == '男'}">
                <input type="radio" name="gender" value="男" checked/>男
                <input type="radio" name="gender" value="女"  />女
            </c:if>

            <c:if test="${user.gender == '女'}">
                <input type="radio" name="gender" value="男" />男
                <input type="radio" name="gender" value="女" checked="checked"/>女
            </c:if>
        </div>

        <div class="form-group">
            <label for="email">邮箱：</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="请输入邮箱" />
        </div>

        <div class="form-group">
            <label for="studentId">学号：</label>
            <input type="text" class="form-control" id="studentId" name="studentId" value="${user.studentId}" placeholder="请输入学号"/>
        </div>

        <div class="form-group">
            <label for="college">学院：</label>
            <input type="text" class="form-control" id="college" name="college" value="${user.college}" placeholder="请输入学院"/>
        </div>

        <div class="form-group">
            <label for="category">组别：</label>
            <input type="text" class="form-control" name="category" id="category" placeholder="请输入组别"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" onreset/> <%--刷新当前页面--%>
            <input class="btn btn-default" type="button" value="返回" onclick="back()"/>
        </div>
    </form>
</div>
</body>
</html>