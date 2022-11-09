<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/15
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container container-small">
    <h1>登录
        <small>没有账号？<a href="signup.jsp">注册</a></small>
    </h1>
    <form action="${pageContext.request.contextPath}/login_deal" method="post">
        <div class="form-group">
            <label>账户/手机/邮箱</label>
            <input type="text" class="form-control" name="account">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" class="form-control" name="password">
        </div>
        <div>
            <p class="alert-danger">${error}</p>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-block" type="submit">登录</button>
        </div>
        <div class="form-group">
            <a href="#">忘记密码？</a>
        </div>
    </form>
</div>
<%@include file="commons/footer.jsp"%>
</body>
</html>
