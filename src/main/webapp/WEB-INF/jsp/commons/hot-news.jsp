<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.cdvtc.news.dao.NewsDao" %>
<%@ page import="com.cdvtc.news.dao.impl.NewsDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cdvtc.news.model.News" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/17
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--    NewsDao newsDao = new NewsDaoImpl();--%>
<%--    List<News> hotNews = newsDao.getHotNews();--%>
<%--    pageContext.setAttribute("hotNews", hotNews);--%>
<%--%>--%>
<div class="side-bar-card">
    <div class="card-title">24小时热闻</div>
    <div class="card-body">
        <div class="list">
            <c:forEach items="${hotNews}" var="news">
                <div class="item">
                    <a class="title" href="${pageContext.request.contextPath}/news/${news.id}">${news.title}</a>
                    <div class="desc">${news.clickCount}阅读　${news.commentNum}评论</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
