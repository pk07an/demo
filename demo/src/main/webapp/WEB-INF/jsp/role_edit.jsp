<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/resources/html/newMain.html" flush="true" />
<body style="background:#fff;">
<div class="col-lg-12" style="margin-top:20px;">
	<ol class="breadcrumb">
      <li><a href="jump_role_list.xhtml?pageNum=${pageNum}">角色列表</a></li>
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
	        <label>角色名称：</label>
	        <input placeholder="必填" id="name" name="name" class="form-control" <c:if test="${operType == 'query'}">readonly</c:if> >
	    </div>
	    <div class="form-group">
	        <label>角色代码：</label>
	        <input id="code" name="code" class="form-control" value="" placeholder="必填，不可重复" />
	    </div>
	    <div class="form-group">
	        <label style="vertical-align:top;">描述：</label>
	        <textarea rows="3" class="form-control" id="description" name="description"></textarea>
	    </div>
	    <div class="form-group">
	    	<label></label>
		    <a id="saveBtn" class="btn btn-primary btn-sm" type="button" <c:if test="${operType=='query'}">style="display:none;"</c:if>>保存</a>
		    <a id="saveBtn" href="jump_role_list.xhtml?pageNum=${pageNum}" class="btn btn-default btn-sm" type="button">返回</a>
	    </div>
	</form>
</div>
<!-- 
	<div>
		<span>角色名称：</span><span style="margin-left: 30px;"><input
			type="text" id="name" name="name" value="" placeHolder="必填" /></span>
	</div>
	<div>
		<span>角色代码：</span><span style="margin-left: 30px;"><input
			type="text" id="code" name="code" value="" placeHolder="必填，不可重复" /></span>
	</div>
	<div>
		<span>描述：</span><span style="margin-left: 30px;"><textarea id="description" name="description"></textarea></span>
	</div>
	<div>
		<a id="saveBtn" href="#"  <c:if test="${operType=='query'}">style="display:none;"</c:if> class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">保存</a>
			<a id="saveBtn" href="jump_role_list.xhtml?pageNum=${pageNum}"  class="easyui-linkbutton">返回</a>
	</div> -->
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
			}
		});

		//按钮事件
		var btnObj = {};
		$.extend(btnObj, {
			bindEvent : function() {
				$("#saveBtn").click(function() {
					if(!validateObj.validate()){
						return false;
					}
					var operType="${operType}";
					var operName="";
					if(operType=="add"){
						operName = "添加";
					}else{
						operName = "修改";
					}
					var adminRoleObj = {};
					$.ajax({
						type : "POST",
						url : "save_role_info.json",
						data : {
							id : "${id}",
							name : $("#name").val(),
							code : $("#code").val(),
							description : $("#description").val()
						},
						dataType : "json",
						success : function(result) {
							if (!result.errorCode) {
								alert("用户角色"+operName+"成功！");
								window.location = "jump_role_list.xhtml?pageNum=${pageNum}";
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
					url : "get_role_by_id.json",
					data : {
						id :id
					},
					dataType : "json",
					success : function(result) {
						if (!result.errorCode) {
							var data = result.data;
							$("#name").val(data.name);
							$("#code").val(data.code);
							$("#description").val(data.description);
							if(operType=="query"){
								$("#name").attr("readonly",true);
								$("#code").attr("readonly",true);
								$("#description").attr("disabled",true);
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
				var name = $("#name").val();
				if(!name){
					alert("角色名称必须输入！");
					return false;
				}
				var code = $("#code").val();
				if(!code){
					alert("角色代码必须输入！");
					return false;
				}
				return true;
			}
		});

		//初始化
		$(document).ready(function() {
			initObj.init();
			btnObj.bindEvent();
		});
	})();
</script>
</html>