<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/15
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.cdvtc.news.dao.NewsDao" %>
<%@ page import="com.cdvtc.news.dao.impl.NewsDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cdvtc.news.model.News" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>新闻发布系统</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>
<div class="container">
  <div class="row">
    <div class="col-sm-2">
      <jsp:include page="commons/side-bar.jsp"></jsp:include>
    </div>
    <div class="col-sm-7">
      <div class="news-list">
<%--        <%--%>
<%--          //获取分类和标签参数--%>
<%--          String category = request.getParameter("category");--%>
<%--          String tag = request.getParameter("tag");--%>

<%--          NewsDao newsDao = new NewsDaoImpl();--%>
<%--          List<News> newsList;--%>
<%--          if(category != null) {--%>
<%--            newsList = newsDao.getNewsByCategory(Integer.valueOf(category));--%>
<%--          } else if(tag != null) {--%>
<%--            newsList = newsDao.getNewsByTag(Integer.valueOf(tag));--%>
<%--          } else {--%>
<%--            newsList = newsDao.getStickNews(10); //默认查询置顶（首页）新闻--%>
<%--          }--%>
<%--//          List<News> newsList = newsDao.getAllNews();--%>
<%--          pageContext.setAttribute("newsList", newsList);--%>
<%--        %>--%>
        <c:forEach items="${newsList}" var="news">
          <div class="news-list-item clearfix">
            <div class="col-xs-5">
              <img src="img/${news.img}">
            </div>
            <div class="col-xs-7">
              <a href="${pageContext.request.contextPath}/news/${news.id}" class="title">${news.title}</a>
              <div class="info">
                <span class="avatar"><img src="img/logo.png"></span>
                <span>${news.editor.name}</span>•
                <span>${news.commentNum}评论</span>•
                <span>${news.pubDateInterval}</span>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <div class="col-sm-3">
      <div class="search-bar">
        <input type="search" class="form-control" placeholder="搜一下">
      </div>
      <div class="side-bar-card flag clearfix">
        <div class="col-xs-5">
          <img src="img/1-1.png">
        </div>
        <div class="col-xs-7">
          <div class="text-lg">有害信息举报专区</div>
          <div>举报电话：12377</div>
        </div>
      </div>
      <jsp:include page="commons/hot-news.jsp"></jsp:include>
    </div>
  </div>
</div>
<%@include file="commons/footer.jsp"%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
