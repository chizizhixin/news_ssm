<%@ page import="com.cvtc.news.model.User" %>
<%@ page import="com.cvtc.news.util.Md5Util" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.cvtc.news.dao.UserDao" %>
<%@ page import="com.cvtc.news.dao.impl.UserDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/20
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * 获取参数
     */
    request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
    String account = request.getParameter("account");
    String nickname = request.getParameter("nickname");
    String mobile = request.getParameter("mobile");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String birthday = request.getParameter("birthday");
    String photo = request.getParameter("photo");

%>
