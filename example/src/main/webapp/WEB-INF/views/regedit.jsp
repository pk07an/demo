<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<form class="form-signin" action="#" method="post" id="sForm">
			<h2 class="form-signin-heading"></h2>
			<input class="input-block-level" placeholder="用户名" id="name"
				name="name" type="text" /> <input class="input-block-level"
				placeholder="密码" id="password" name="password" type="password" />
			<button class="btn btn-large btn-primary" type="button" id="submit">确定</button>
		</form>

	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#submit").bind("click", function() {
				var name = $("#name").val();
				var password = $("#password").val();
				$.ajax({
					type : "POST",
					url : "create",
					data : {
						"name" : name,
						"password" : password
					},
					dataType : "json",
					success : function(json) {
						if (json.status == 99) {
							alert("用户名重复");
							return;
						} else {
							alert(json.status);
							alert("注册成功");
							window.location = "/example/";
						}

					}
				});

			});
		});
	</script>
</body>
</html>