<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/resources/html/common.html" flush="true" />
<body id="body">
	<h5>
		<span>系统菜单树</span>
	</h5>
	<div>
		<span>用户名称：</span><span><input id="userName" name="userName" value="" /></span>
		<span><input type="button" id="search" name="search" value="搜索" /></span>
	</div>
	<div style="margin-top: 20px;">
		<table id="adminUserList" class="easyui-datagrid" style="height: auto"
			data-options="fitColumns:true,singleSelect:true">
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
</script>
</html>