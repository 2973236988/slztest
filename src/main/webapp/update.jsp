<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改文章</title>



</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改文章</h3>
    <form action="${pageContext.request.contextPath}/articleListServlet/update" method="post">
        <!--  隐藏域 提交id-->
        <input type="hidden" name="author" value="${article.author}">

        <div class="form-group">
            <label for="author">作者：</label>
            <input type="text" class="form-control" id="author" name="author"  value="${article.author}" readonly="readonly" placeholder="请输入姓名" />
        </div>

        <div class="form-group">
            <label for="articleTitle">文章标题：</label>
            <input type="text" class="form-control" value="${article.articleTitle}" id="articleTitle"  name="articleTitle" placeholder="请输入文章标题" />
        </div>



        <div class="form-group">
            <label for="articleContent">文章内容：</label>
            <input type="text" id="articleContent" class="form-control" value="${article.articleContent}" name="articleContent" placeholder="请输入文章内容"/>
        </div>


        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>