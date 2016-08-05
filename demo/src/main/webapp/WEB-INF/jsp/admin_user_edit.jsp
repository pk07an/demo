<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/resources/html/newMain.html" flush="true" />
<body id="body" style="background:#fff;">
<div class="col-lg-12" style="margin-top:20px;">
	<ol class="breadcrumb">
      <li><a href="jump_admin_user_list.xhtml?pageNum=${pageNum}">用户列表</a></li>
      <li class="active">
	      <c:if test="${operType=='add'}">增加</c:if>
	      <c:if test="${operType=='query'}">查看</c:if>
	      <c:if test="${operType=='edit'}">修改</c:if>
      </li>
    </ol>
</div>
<div class="col-lg-12" style="margin-top:10px;">
	<form role="form">
		<div class="form-group">
	        <label>用户名称：</label>
	        <input placeholder="必填，不可重复" id="userName" AUTOCOMPLETE="off" name="userName" value="" class="form-control" <c:if test="${operType == 'query'}">readonly</c:if> >
	    </div>
	    <c:if test="${operType ne 'query'}">
		    <div class="form-group">
		        <label>用户密码：</label>
		        <input type="password" id="password" AUTOCOMPLETE="off" name="password" class="form-control" value="" placeHolder="必填" />
		    </div>
	    <div class="form-group">
	        <label>确认用户密码：</label>
	        <input
				type="password" id="confirmPassword" name="confirmPassword" class="form-control" value="" placeHolder="必填" />
	    </div>
	    </c:if>
	    <div class="form-group">
	        <label>用户角色：</label>
	        <select id="role" class="form-control" name="role" <c:if test="${operType == 'query'}">disabled</c:if>>
	          <option value="">请选择</option>
	          <c:forEach items="${adminRoleList}" var="adminRole">
	              <option value="${adminRole.code}">${adminRole.code} - ${adminRole.name}</option>
	          </c:forEach>
	        </select>
	    </div>
	    <div class="form-group">
	    	<label></label>
		    <a id="saveBtn" class="btn btn-primary btn-sm" type="button" <c:if test="${operType=='query'}">style="display:none;"</c:if>>保存</a>
		    <a href="jump_admin_user_list.xhtml?pageNum=${pageNum}" class="btn btn-default btn-sm" type="button">返回</a>
	    </div>
	</form>
</div>
</body>
<script type="text/javascript">
	(function() {

		//初始化模块
		var initObj = {};
		$.extend(initObj, {
			init : function() {
				var id= "${id}";
				if(id){
					dataObj.loadData(id);
				}
				btnObj.bindEvent();
			}
		});

		//按钮事件
		var btnObj = {};
		$.extend(btnObj, {
			bindEvent : function() {
				$("#saveBtn").click(function() {
					
					var operType="${operType}";
					var operName="";
					if(operType=="add"){
						operName = "添加";
					}else{
						operName = "修改";
					}
					if(!validateObj.validate()){
						return false;
					}
					$.ajax({
						url : "save_admin_user_info.json",
						type : "POST",
						data : {
							id : "${id}",
							name : $("#userName").val(),
							pwd : $("#password").val(),
							role : $("#role").val()
						},
						success : function(result) {
							if (!result.errorCode) {
								alert("用户"+operName+"成功！");
								window.location = "jump_admin_user_list.xhtml?pageNum=${pageNum}";
							}else{
								alert(result.errorMsg);
							}
						}

					})
				});
			}
		});
		
		//数据模块
		var dataObj = {};
		$.extend(dataObj,{
			loadData : function(id){
				var operType="${operType}";
				$.ajax({
					type : "POST",
					url : "get_admin_user_by_id.json",
					data : {
						id :id
					},
					dataType : "json",
					success : function(result) {
						if (!result.errorCode) {
							var data = result.data;
							$("#userName").val(data.userName);
							$("#role").val(data.role);
							if(operType != "query"){
								$("#password").val(data.password);
								$("#confirmPassword").val(data.password);
							}
						}
					}

				})
			}
		});
		
		
		//数据校验模块
		var validateObj = {};
		$.extend(validateObj,{
			validate : function(){
				var name = $("#userName").val();
				if(!name){
					$.messager.alert("警告","用户名称必须输入！");
					return false;
				}
				var role = $("#role").val();
				if(!role){
					$.messager.alert("警告","请选择用户角色！");
					return false;
				}
				var pwd = $("#password").val();
				var cmfpwd = $("#confirmPassword").val();
				if(pwd == "" || pwd == undefined || pwd == null){
					$.messager.alert("警告","用户密码必须输入！");
					return false;
				}
				if(pwd.length<6 || pwd.length>15){
					$.messager.alert("警告","用户密码必须是6到15位！");
					return false;
				}
				if(cmfpwd == "" || cmfpwd == undefined || cmfpwd == null){
					$.messager.alert("警告","确认用户密码必须输入！");
					return false;
				}
				if(cmfpwd.length<6 || cmfpwd.length>15){
					$.messager.alert("警告","确认用户密码必须是6到15位！");
					return false;
				}
				if(pwd != cmfpwd){
					$.messager.alert("警告","用户密码和确认用户密码必须一致！");
					return false;
				}
				return true;
			}
		});

		//初始化
		$(document).ready(function() {
			initObj.init();
		});
	})();
</script>
</html>