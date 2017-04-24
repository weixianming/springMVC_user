<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css" type="text/css">
<title><decorator:title default="欢迎使用文档管理系统"/></title>
<decorator:head/>
</head>
<body>
<c:choose>
	<c:when test="${empty loginUser}">
		<a href="login">登录</a>
	</c:when>
	<c:otherwise>
		欢迎您${loginUser.nickname}
	</c:otherwise>
</c:choose>
<hr/>
<h3 align="center"><decorator:title default="用户管理"/></h3>
<decorator:body/>
<div align="center" style="width:100%;border-top:1px solid; float:left;margin-top:10px;">
	CopyRight@2016-2017<br/>
	开发项目
</div>
</body>
</html>