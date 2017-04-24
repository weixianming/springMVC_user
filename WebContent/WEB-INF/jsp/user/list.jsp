<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css">
<title>用户列表</title>
</head>
<body>
<div id="t1">
	<table class="ct">
		<tr><td>用户名</td><td>用户昵称</td><td>邮箱</td><td colspan="2">操作</td></tr>
		<c:forEach items="${pages.datas}" var="user">
		<tr>
			<td><a href="${user.id}/show">${user.username }</a></td>
			<td>${user.nickname }</td>
			<td>${user.email }</td>
			<td><a href="${user.id}/delete">删除</a></td>
			<td><a href="${user.id}/update">更新</a></td>
		</tr>
		</c:forEach>
			<tr>
	<td colspan="7">
		<jsp:include page="/inc/pager.jsp">
			<jsp:param value="list" name="url" />
			<jsp:param value="${pages.totalRecord }" name="items" />
		</jsp:include></td>
	</tr>
	</table>
</div>
</body>
</html>