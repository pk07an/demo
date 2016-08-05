<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;background:#fff;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/resources/html/login.html" flush="true" />
<title>login</title>
</head>


<body>
<div class="blog-masthead" style="background:#fff;padding:20px 0;border-bottom:2px solid #CEDBE1;">
	<div class="container">
		<nav class="blog-nav">
		    <span><label style="font-size:25px"><Strong>招商信诺后台管理系统DEMO</Strong></label></span>
		</nav>
	</div>
</div>
<div style="background:#E7F4FC;border-bottom:2px solid #CEDBE1;">
<form id="login" method="post" action="login.xhtml">
	<div class="container login-bg">
        <div class="row">
            <div class="col-md-4 col-md-offset-7">
                <div class="login-panel panel panel-default">
                    <div class="panel-body">
                            <fieldset>
                                <div class="form-group">
                                    <input style="width:65%;" class="form-control" placeholder="用户名 " id="userName" name="userName" type="text" autofocus data-rule="用户名:required;"> 
                                </div>
                                <div class="form-group">
                                    <input style="width:65%;" class="form-control" placeholder="密码" id="pwd" name="pwd" type="password" value="" data-rule="密码:required;">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="#" style="width:65%;" onclick="loginBtn();" class="btn btn-lg btn-success btn-block">登录</a>
                                
                            </fieldset>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</div>
<script type="text/javascript">
		function loginBtn(){
			$("#login").submit();
		}
		$(document).keydown(function(event){
			if(event.keyCode==13){
				$("#login").submit();
			}
		})
		$(document).ready(function(){
			var errorMsg = "${errorMsg}";
			if(errorMsg != null && errorMsg != undefined && errorMsg != ""){
				alert(errorMsg);
			}
		});
		
</script>
</body>