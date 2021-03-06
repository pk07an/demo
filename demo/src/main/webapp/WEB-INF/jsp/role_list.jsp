<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/resources/html/newMain.html" flush="true" />
<body id="body" style="background:#fff;">
	<div class="col-lg-12">
		<h4 class="page-header">角色管理</h4>
	</div>
	<div class="col-lg-12">
		<a id="btn" href="jump_role_edit.xhtml?operType=add" type="button" class="btn btn-primary btn-sm"><i class="fa fa-plus-circle"></i>  增加角色</a>
	</div>
	<div class="col-lg-12" style="margin-top:10px;">
		<div style="width:280px;display:inline-block;">
			<div class="form-group input-group">
		        <span class="input-group-addon">角色名称：</i>
		        </span>
		        <input type="text" id="name" name="name" placeholder="角色名称" class="form-control">
		    </div>
	    </div>
	    <div style="width:280px;display:inline-block;">
		    <div class="form-group input-group">
		        <span class="input-group-addon">角色代码：</i>
		        </span>
		        <input type="text" id="code" name="code" placeholder="角色代码" class="form-control">
		    </div>
	    </div>
	    <div style="width:280px;display:inline-block;">
	    	<div class="form-group input-group">
	    		<button id="search" name="search" class="btn btn-primary" type="reset"><i class="fa fa-search"></i>   查询</button>
			</div>
	    </div>
	</div>
	<div style="margin-top:-15px;">
		<table id="roleList" title="角色" class="easyui-datagrid" style="height: auto"
			data-options="fitColumns:true,singleSelect:true">
			<thead>
				<tr>
					<th data-options="field:'id',width:fixWidth(0.1),hidden:true">序号</th>
					<th data-options="field:'name',width:fixWidth(0.15)">角色名称</th>
					<th data-options="field:'code',width:fixWidth(0.1)">角色代码</th>
					<th data-options="field:'description',width:fixWidth(0.2)">描述</th>
					<th data-options="field:'createTimeDesc',width:fixWidth(0.15)">创建时间</th>
					<th data-options="field:'updateTimeDesc',width:fixWidth(0.15)">修改时间</th>
					<th
						data-options="field:'_operate',width:fixWidth(0.15),formatter:formatOper">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript">
	function editRole(index, operType) {
		$('#roleList').datagrid('selectRow', index);// 关键在这里  
		var row = $('#roleList').datagrid('getSelected');
		if (row) {
			if (operType == 'query' || operType == 'edit') {
				var pageNum = $('.pagination-num').val();
				window.location.href = "/demo/jump_role_edit.xhtml?operType="
						+ operType + "&id=" + row.id+"&pageNum="+pageNum;
			} else if (operType == 'del') {
				if (window.confirm('你确定要删除吗？')) {
					$.ajax({
						type : "POST",
						url : "delete_role_by_id.json",
						data : {
							id :row.id
						},
						dataType : "json",
						success : function(result) {
							if (!result.errorCode) {
								alert("用户角色删除成功！");
								window.location = "jump_role_list.xhtml";
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
		return '<a href="#" onclick="editRole(\''
				+ index
				+ '\',\'query\')">查看</a>&nbsp;&nbsp<a href="#" onclick="editRole(\''
				+ index
				+ '\',\'edit\')">修改</a>&nbsp;&nbsp<a href="#" onclick="editRole(\''
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
					url : "load_role_list.json",
					data : {
						name : $("#name").val(),
						code : $("#code").val(),
						pageNumber : pageNumber,
						pageSize : pageSize
					},
					dataType : "json",
					success : function(result) {
						if (!result.errorCode) {
							$('#roleList').datagrid({
								pagination : true
							});
							$("#roleList").datagrid("loadData", {
								"total" : result.data.total,
								"rows" : result.data.rows
							});
							var pg = $("#roleList").datagrid("getPager");
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