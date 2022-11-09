<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.cdvtc.news.dao.*" %>
<%@ page import="com.cdvtc.news.dao.impl.*" %>
<%@ page import="com.cdvtc.news.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.cdvtc.news.util.CommentUtil" %><%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/15
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/quill.snow.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/quill-emoji.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>

<div class="container">
    <div class="col-xs-8">

        <h2 class="news-title">${news.title}
        </h2>
        <div class="news-status">
            ${news.clickCount}阅读 • ${news.pubDateInterval}
            <c:forEach items="${tagSet}" var="tag">
                <div class="label label-default">${tag.name}
                </div>
            </c:forEach>
        </div>
        <div class="news-content">
            ${news.content}
        </div>
        <div>
            <h3>我要评论</h3>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/sendComment/${id}" method="post"
                  onsubmit="return setContent()">
                <div id="editor">
<%--                    ${news.content}--%>
                </div>
                <input type="hidden" name="content" id="content">
                <%--                    <input type="hidden" name="content">--%>
                <button type="submit" class="btn btn-default" <c:if test="${empty sessionScope.user}">disabled</c:if>>
                    发布
                </button><c:if test="${empty sessionScope.user}">您还未登陆，请先<a href="${pageContext.request.contextPath}/login">登陆</a></c:if>
            </form>
        </div>
        <h3>最新评论</h3>
        <c:forEach items="${comments}" var="comment">
<%--            <jsp:include page="/comment/${comment.id}"></jsp:include>--%>
            <jsp:include page="commons/comment.jsp">
                <jsp:param name="commentId" value="${comment.id}"/>
            </jsp:include>
        </c:forEach>
    </div>
    <div class="col-xs-4">
        <div class="side-bar-card">
            <div class="card-title">相关推荐</div>
            <div class="card-body">
                <div class="list">
                    <c:forEach items="${recommendedNews}" var="n">
                    <div class="item clearfix">
                        <div class="col-xs-5 no-padding-h">
                            <img src="${pageContext.request.contextPath}/img/${n.img}" alt="#">
                        </div>
                        <div class="col-xs-7">
                            <div class="title"><a href="${pageContext.request.contextPath}/news/${n.id}">${n.title}
                            </div>
                            <div class="desc">${n.clickCount}阅读 ${n.commentNum}评论</div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <jsp:include page="commons/hot-news.jsp"></jsp:include>
    </div>
</div>
<%@include file="commons/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/quill.min.js"></script>
<script src="${pageContext.request.contextPath}/js/quill-emoji.js"></script>
<script>
    var toolbarOptions = {
        container: [
            ['bold', 'italic', 'underline', 'strike'],
            ['blockquote', 'code-block'],
            ['emoji']
        ],
        handlers: {
            'emoji': function () {}
        }
    };

    var quill = new Quill('#editor', {
        modules: {
            "toolbar": toolbarOptions,
            "emoji-toolbar": true,
            "emoji-shortname": true,
            "emoji-textarea": true
        },
        theme: 'snow'
    });
    var replyQuill; //定义全局变量

    function setContent() {
        if(quill && quill.getLength() > 1){
            $("#content").val(quill.root.innerHTML);
            return true;
        } else{
            alert("内容不能为空！")
            return false;
        }
    }

    function setReplyContent() {
        if(replyQuill && replyQuill.getLength() > 1){
            $("#reply-content").val(replyQuill.root.innerHTML);
            return true;
        } else {
            alert("内容不能为空！")
            return false;
        }
    }

    function reply(commentId) {
        $(".editor-form").remove(); //清除编辑表单
        $(".btn-reply").removeAttr("disabled"); // 恢复按钮功能

        $("#btn-"+commentId).attr("disabled", "disabled");  //停止重复点击
        $("#comment-"+commentId).append("<form action='${pageContext.request.contextPath}/sendComment/${id}?replyForId="+commentId
            +"' class='editor-form' method='post' onsubmit='return setReplyContent()'><div id='ql-editor'></div><input type='hidden' name='content' id='reply-content'><button class='btn btn-default'"
             <c:if test="${empty sessionScope.user}">+ ' disabled'</c:if> + ">回复</button>"
             <c:if test="${empty sessionScope.user}">+ '您还未登陆，请先<a href="login.jsp">登陆</a>'</c:if> + "</form>");
        replyQuill = new Quill('#ql-editor', {
            modules: {
                "toolbar": toolbarOptions,
                "emoji-toolbar": true,
                "emoji-shortname": true,
                "emoji-textarea": true
            },
            theme: 'snow'
        });
    }

    function like(commentId) {
        var like = $.cookie('comment-like-'+commentId);  // 从Cookie中获取点赞记录
        var operate = like? 'unlike': 'like';

        $.post('${pageContext.request.contextPath}/commentLike/'+commentId + '/' + operate, function (num) {
            // 更新点赞数和图标状态
            $('#like-num-'+commentId).text(num > 0 ? num : '赞');
            if(like){
                $('#like-icon-'+commentId).removeClass('alert-danger');
                $.removeCookie('comment-like-'+commentId, { path: "/"});  // 删除Cookie
            } else {
                $('#like-icon-'+commentId).addClass('alert-danger');
                $.cookie('comment-like-'+commentId, true, { path: "/"}); // 写入Cookie
            }
        });
    }

    function dislike(commentId) {
        var dislike = $.cookie('comment-dislike-'+commentId);  // 从Cookie中获取点踩记录
        var operate = dislike? 'undislike': 'dislike';

        $.post('${pageContext.request.contextPath}/commentLike/'+commentId + '/' + operate, function (num) {
            // 更新点赞数和图标状态
            $('#dislike-num-'+commentId).text(num > 0 ? num : '踩');
            if(dislike){
                $('#dislike-icon-'+commentId).removeClass('alert-danger');
                $.removeCookie('comment-dislike-'+commentId, { path: "/"});  // 删除Cookie
            } else {
                $('#dislike-icon-'+commentId).addClass('alert-danger');
                $.cookie('comment-dislike-'+commentId, true, { path: "/"}); // 写入Cookie
            }
        });
    }
</script>
</body>
</html>
