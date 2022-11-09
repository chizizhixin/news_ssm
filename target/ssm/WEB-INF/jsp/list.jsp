<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/30
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书列表</title>
    <%@ include file="common/head.jsp"%>
    <h1>demo</h1>
</head>
<body>
<div class="container">
   <table class="table">
       <thead>
       <tr>
           <th>编号</th>
           <th>书名</th>
           <th>数量</th>
       </tr>
       </thead>
       <tbody>
          <c:forEach items="${list}" var="book">
              <tr>
                  <td>
                      <a href="${pageContext.request.contextPath}/book/${book.bookId}/detail">
                              ${book.bookId}
                      </a>
                  </td>
                  <td>
                      ${book.name}
                  </td>
                  <td>
                      ${book.number}
                  </td>
              </tr>
          </c:forEach>
       </tbody>

   </table>
</div>
</body>
</html>
