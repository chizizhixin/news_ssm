<%@ page import="com.cdvtc.news.dao.UserDao" %>
<%@ page import="com.cdvtc.news.dao.impl.UserDaoImpl" %>
<%@ page import="com.cdvtc.news.model.User" %>
<%@ page import="com.cdvtc.news.util.Md5Util" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/19
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String account = request.getParameter("account");
    String password = request.getParameter("password");

%>
