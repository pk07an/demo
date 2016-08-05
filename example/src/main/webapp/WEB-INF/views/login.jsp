<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Wellcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form class="form-signin" action="main" method="post" id="sForm">
				<h2 class="form-signin-heading"></h2>
				<input class="input-block-level" placeholder="用户名" name="name" type="text"/>
				<input class="input-block-level" placeholder="密码" name="password" type="password"/>
				<button class="btn btn-large btn-primary" type="submit" id="submit">登 录</button>
				<a class="btn btn-link" href="regedit">注册</a>
				<p style="color:red">${errorMsg}</p>
		</form>
			
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
		});
	</script>
</body>
</html>