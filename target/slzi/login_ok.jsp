<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
</head>
<body>
    <div style="text-align:center;red:yellow;font-weight:bold;height:150px;padding-top:100px;font-size:30px;">
        <h4>恭喜，登录成功！</h4>
    </div>
    <div align="center">
        <a
                href="${pageContext.request.contextPath}/user/findUserByPage" style="text-decoration:none;font-size:33px">查询所有用户信息
        </a>
    </div>
</body>
</html>