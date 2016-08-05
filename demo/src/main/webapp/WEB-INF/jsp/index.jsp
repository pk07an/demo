<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/resources/html/login.html" flush="true" />
<title>招商信诺后台管理系统</title>
<link rel="stylesheet" type="text/css" href="/demo/resources/css/themes/default/easyui.css">   
</head>

<body class="easyui-layout">
	<!--<div data-options="region:'north',title:'招商信诺在线微信后台管理系统',split:true"
		style="height: 100px;"></div>
	<div data-options="region:'west',title:'菜单栏',split:true"
		style="width: 100px;">
		<ul id="tt" class="easyui-tree">
			<c:forEach var="menu" items="${adminMenu}">
				<li><span>${menu.name}</span>
					<ul>
						<c:forEach var="child" items="${menu.children}">
							<li><span><a href="${child.href}" target="mainframe">${child.name}</a></span></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
		</ul>
	</div>
	<div data-options="region:'center',title:'欢迎您，${userName}'"
		style="padding: 5px; background: #eee;">
		<iframe id="mainframe" name="mainframe" frameBorder="0" width="100%" scrolling="no" height="100%" 
onload="document.all('mainframe').style.height=mainframe.document.body.scrollHeight+30;"> </iframe>
	</div>
	-->
	
	
	<div id="wrapper">
	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">导航</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </button>
		  <label style="font-size:25px"><Strong>招商信诺后台管理系统DEMO</Strong></label>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown">
				<p>欢迎您,<strong>${userName}</strong></p>
			</li>
			<li class="dropdown">
			<a href="#" >
					<span onclick="ChangePwd();">修改密码</span>
				</a>
			</li>
			<li class="dropdown">
				<a href="#" >
					<i class="fa fa-sign-out fa-fw"></i> <span onclick="logOut();">退出</span>
				</a>
			</li>
		</ul>
		<div role="navigation" class="navbar-default sidebar">
			<div class="sidebar-nav navbar-collapse">
				<ul id="side-menu" class="nav">
				<c:forEach var="menu" items="${adminMenu}">
						<li class="active">
							<a href="#" ><i class="fa ${menu.description} fa-fw"></i>  ${menu.name}<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<c:forEach var="child" items="${menu.children}">
									<li><a href="${child.href}" target="mainframe">${child.name}</a></li>
								</c:forEach>
							</ul>
						</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</nav>
	<div id="page-wrapper" style="padding:0;">
		<!--<iframe src="" scrolling="none" style="border:none;width:100%;height:100%;">-->
		<iframe id="mainframe" name="mainframe" onload="document.all('mainframe')  marginwidth="0" marginheight="0" border="0" scrolling="auto" frameborder="0"
width="100%"></iframe>
	</div>
</div>

<div id="pwdChange" style="padding: 10%;"  class="easyui-dialog" data-options="iconCls:'icon-save',closed:true">
	<form id="ff" method="post">
		<table class="formTable">
			<tr style="padding-top: 10px;">
				<td align="right">旧密码<span style="color: red;">*</span></td>
				<td style="padding-left: 5px;"><input type="text" id="oldWord" name="oldWord"/></td></tr>
			<tr style="padding-top: 10px;">
				<td align="right">新密码<span style="color: red;">*</span></td>
				<td style="padding-left: 5px;"><input type="password" id="newWord" name="newWord" id="" /></td>
			</tr>
			<tr style="padding-top: 10px;">
				<td align="right">确认密码<span style="color: red;">*</span></td>
				<td style="padding-left: 5px;"><input type="password" id="repeatWord" name="repeatWord" id="" /></td>
			</tr>
			<tr style="padding-top: 5px;">
				<td align="right"></td>
				<td style="padding-left: 5px;"><input type="submit" class="btn btn-primary btn-sm"  value="提交"/></td>
			</tr>
		</table>
	</form>
	
</div>
</body>

<script type="text/javascript">

		$('#ff').validator({
			theme: 'yellow_right',
			timely:1,
			rules:{
				unique:function(element, param, field){ //自定义方法规则
					var data = "oldWord="+$("#oldWord").val();
					return $.ajax({
						type:"POST",
						url:"../demo/existUser.json",
						data:data,
						success:function(data){
							return true;
						},
						error:function (XMLHttpRequest, textStatus, errorThrown){
							$.messager.alert('系统异常','验证请等待');
							return false;
						}
					});
				}
			},
			fields:{
				"oldWord":{
					rule:"旧密码:required;unique;"
				},
				"newWord":{
					rule:"新密码:required"
				},
				"repeatWord":{
					rule:"确认密码:required;match(newWord)"
				}
			},
			// 表单验证通过后，ajax提交
			valid: function(form){
				$.ajax({
					url: 'changePassWord.json',
					type: 'post',
					data: $(form).serialize(),
					dataType: 'json',
					success: function(d){
						$.messager.alert('系统异常','修改密码成功,请重新登陆',"info", function () {
			    			$('#pwdChange').dialog('close');
				    		window.location.href='../demo';
			            });
					}
				});
			}
		});
	 

		function ChangePwd(){
			 $("#oldWord").val("");
			 $("#newWord").val("");
			 $("#repeatWord").val("");
			$('#pwdChange').dialog({
				 title: '修改密码',    
				    width: 400,    
				    height: 300,    
				    closed: false,    
				    href: '',    
				    modal: true   
			});
		}
		
		function logOut(){
			$.ajax({
				type:"post",
				url:"logOut.json",
				success:function(data){
					window.location.href='../demo';
				},
				error:function(){}
			});
		}

		
</script>
</html>