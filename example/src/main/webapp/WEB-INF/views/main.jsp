<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<div>
		欢迎：${userVo.name}，您的密码是：${userVo.password}
	</div>
	
		<div>
			<table class="table table-bordered table-hover">
			<thead>
				<tr> <th colspan="3">用户列表</th></tr>
				<tr>
					<th>用户名</th>
					<th>密码</th>
					<th>注册日期</th>
				</tr>
			</thead>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.password}</td>
						<td>${user.createDate}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center">
			<a class="btn btn-large btn-primary" href="/example">返回</a>
		</div>
	</div>
</body>
</html>