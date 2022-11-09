<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2022/10/31
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书详情</title>
    <%@ include file="common/head.jsp"%>
</head>
<body>
<div class="container">
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            图书详情
            <a href="${pageContext.request.contextPath}/book/list">返回列表</a>
        </h3>
    </div>
    <div class="panel-body">
        <div class="list-group">
            <a href="#" class="list-group-item">
                <h4 class="list-group-item-heading">
                    书名：
                </h4>
                <p class="list-group-item-text">
                    ${book.name}
                </p>
            </a>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item">
                <h4 class="list-group-item-heading">
                    数量
                </h4>
                <p class="list-group-item-text">
                    ${book.number}
                </p>
            </a>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item">
                <h4 class="list-group-item-heading">
                    编号：
                </h4>
                <p class="list-group-item-text">
                    ${book.bookId}
                </p>
            </a>
        </div>
    </div>
    <div class="panel-footer text-right">
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">预约</button>

        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">预约图书</h4>
                    </div>
                    <div class="modal-body">
                        学号：<input name="studentId" id="studentId">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" onclick="appoint()">确定</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
    </div>
</div>
</div>
</body>
<script>
    function appoint(){
        $.post("${pageContext.request.contextPath}/book/${book.bookId}/appoint", {studentId: $('#studentId').val()}, function (result){
            if(result.success) {
                alert("预约成功.");
                window.location.href = "${pageContext.request.contextPath}/book/${book.bookId}/detail";  // 刷新页面
             } else {
                alert("预约失败!"+result.error);
            }
        });
        $('#myModal').modal('hide');
    }
</script>
</html>
