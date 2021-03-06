<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/resources/html/newMain.html" flush="true" />
<body id="body" style="background:#fff;">
	<div class="col-lg-12">
		<h4 class="page-header">用户管理</h4>
	</div>
	<div class="col-lg-12">
		<a id="btn" href="jump_admin_user_edit.xhtml?operType=add" type="button" class="btn btn-primary btn-sm"><i class="fa fa-plus-circle"></i>  增加新用户</a>
	</div>
	<div class="col-lg-12" style="margin-top:10px;">
		
	    <div style="width:280px;display:inline-block;">
		    <div class="form-group input-group">
		        <span class="input-group-addon">用户名称：
		        </span>
		        <input type="text" id="userName" name="userName" placeholder="用户名搜索" class="form-control">
		    </div>
	    </div>
	    <div style="width:280px;display:inline-block;">
	    	<div class="form-group input-group">
	    		<button type="button" class="btn btn-primary" id="search" name="search"><i class="fa fa-search"></i>   查询</button>
			</div>
	    </div>
	</div>
	<div>
		<table id="adminUserList" title="用户" class="easyui-datagrid" style="height: auto"
			data-options="fitColumns:true,singleSelect:true,collapsible:true">
			<thead>
				<tr>
					<th data-options="field:'id',width:fixWidth(0.1),hidden:true">序号</th>
					<th data-options="field:'userName',width:fixWidth(0.2)">用户名称</th>
					<th data-options="field:'role',width:fixWidth(0.1)">用户角色</th>
					<th data-options="field:'createTimeDesc',width:fixWidth(0.2)">创建时间</th>
					<th data-options="field:'updateTimeDesc',width:fixWidth(0.2)">修改时间</th>
					<th
						data-options="field:'_operate',width:fixWidth(0.2),formatter:formatOper">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript">
	function editAdminUser(index, operType) {
		$('#adminUserList').datagrid('selectRow', index);// 关键在这里  
		var row = $('#adminUserList').datagrid('getSelected');
		if (row) {
			if (operType == 'query' || operType == 'edit') {
				var pageNum = $('.pagination-num').val();
				window.location.href = "/demo/jump_admin_user_edit.xhtml?operType="
						+ operType + "&id=" + row.id+"&pageNum="+pageNum;
			} else if (operType == 'del') {
				if (window.confirm('你确定要删除吗？')) {
					$.ajax({
						type : "POST",
						url : "delete_admin_user_by_id.json",
						data : {
							id :row.id
						},
						dataType : "json",
						success : function(result) {
							if (!result.errorCode) {
								alert("用户删除成功！");
								window.location = "jump_admin_user_list.xhtml";
							}
						}

					})
				} else {
					return false;
				}
			}
		}
	}

	function formatOper(val, row, index) {
		return '<a href="#" onclick="editAdminUser(\''
				+ index
				+ '\',\'query\')">查看</a>&nbsp;&nbsp<a href="#" onclick="editAdminUser(\''
				+ index
				+ '\',\'edit\')">修改</a>&nbsp;&nbsp<a href="#" onclick="editAdminUser(\''
				+ index + '\',\'del\')" >删除</a>';
	}

	function fixWidth(percent) {
		return document.body.clientWidth * percent; //这里你可以自己做调整  
	}

	(function() {
		//数据对象
		var dataObj = {};
		$.extend(dataObj, {
			setData : function(pageNumber, pageSize) {
				$.ajax({
					type : "POST",
					url : "get_admin_user_list.json",
					data : {
						userName : $("#userName").val(),
						pageNumber : pageNumber,
						pageSize : pageSize
					},
					dataType : "json",
					success : function(result) {
						if (!result.errorCode) {
							$('#adminUserList').datagrid({
								pagination : true
							});
							$("#adminUserList").datagrid("loadData", {
								"total" : result.data.total,
								"rows" : result.data.rows
							});
							var pg = $("#adminUserList").datagrid("getPager");
							if (pg) {
								$(pg).pagination(
										{
											pageNumber : pageNumber,
											pageSize : pageSize,
											showPageList : false,
											showRefresh : false,
											beforePageText : '第',
											afterPageText : '页，共{pages}页',
											displayMsg : '',
											onSelectPage : function(pageNumber,
													pageSize) {
												dataObj.setData(pageNumber,
														pageSize);
											}
										});
							}
						}
					}

				})
			}
		});
		
		//绑定event事件
		var eventObj = {};
		$.extend(eventObj,{
			bindEvent : function(){
				$("#search").click(function(){
					dataObj.setData(1, 15);
				});
			}
		});

		//初始化对象
		initObj = {};
		$.extend(initObj, {
			init : function() {
				var pageNum = "${pageNum}";
				if(pageNum){
					dataObj.setData(pageNum, 15);
				}else{
					dataObj.setData(1, 15);
				}
				eventObj.bindEvent();
			}
		});

		//初始化
		$(document).ready(function() {
			initObj.init();
		});
	})();
</script>
</html>