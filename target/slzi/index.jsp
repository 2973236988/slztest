<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>石榴仔</title>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        $(function () {
            //查询用户信息
            $.get("user/findOne",{},function (data) {
                //{uid:1,name:'李四'}
                var msg = "欢迎回来，"+data.name;
                $("#span_username").html(msg);

            });
        });

    </script>
</head>
<body>

    <h1 align="center">石榴仔</h1>

    <div class="shortcut">
        <!-- 未登录状态  -->
        <div class="login_out" align="center">
            <a href="login.jsp">登录</a>
        </div>
        <!-- 登录状态  -->
        <div class="login" align="center">

            <span id="span_username"></span>

            <h1><a href="javascript:location.href='${pageContext.request.contextPath}/user/exit';">退出</a></h1>
        </div>
    </div>

    <div align="center">
        <a
                href="${pageContext.request.contextPath}/user/findUserByPage" style="text-decoration:none;font-size:33px">查询所有用户信息
        </a>
    </div>



</body>
</html>