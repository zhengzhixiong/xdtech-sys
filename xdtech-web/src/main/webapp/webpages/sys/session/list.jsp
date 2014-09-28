<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/css.css">
</head>
<body>

<c:if test="${not empty msg}">
    <div class="message">${msg}</div>
</c:if>

当前在线人数：${sessionCount}人<br/>
<table class="table">
    <thead>
        <tr>
            <th style="width: 150px;">会话ID</th>
            <th>用户名</th>
            <th>主机地址</th>
            <th>最后访问时间</th>
            <th>已强制退出</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${sessions}" var="session">
            <tr>
                <td>${session.id}</td>
                <td></td>
                <td>${session.host}</td>
                <td><fmt:formatDate value="${session.lastAccessTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td></td>
                <td>
                    
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>