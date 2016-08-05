<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/demo/resources/js/adminMenu.js"></script>
<jsp:include page="/resources/html/newMain.html" flush="true" />
</head>
<body id="body" style="background:#fff;">
	
	<div class="col-lg-12">
		<h4 class="page-header">系统菜单管理</h4>
	</div>
	
	<div class="col-lg-12">
		<div class="alert alert-warning" style="padding:5px 10px;margin-bottom:10px;"><i class="fa fa-exclamation"></i>  请使用右键操作.</i>
		</div>
		<c:if test="${user.role!='assessor'}">
			<button onclick="addZhuMenu();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-plus-circle"></i>  增加主菜单</button>
		</c:if>
	</div>
	
	<table id="adminMenu" ></table>  
	
	<div id="menu_edit" class="easyui-menu" style="width:110px;display: none" data-options="onClick:menuHandler">   
    			<div data-options="iconCls:'icon-mini-add',name:'addMenu'" >添加菜单</div>
    			<div data-options="iconCls:'icon-mini-edit',name:'updateMenu'">修改菜单</div>
    			<div data-options="iconCls:'icon-remove',name:'delMenu'">删除菜单</div>  
    		<div class="menu-sep"></div>
			<div>退出</div>
	</div>
	
	<div id="addOrUpdate" class="easyui-dialog""
		 data-options="iconCls:'icon-save',closed:true" > 
		<div style="padding:10px 60px 20px 60px">
	    <form id="adminMenuForm" action="" method="post">
	    	<!-- 操作类型：删除、修改，不能删除该隐藏域 -->
	   				<input type="hidden" id="opearType" name="opearType">
	   				<input type="hidden" name="parentId" id="parentId"/>
	    	<table cellpadding="5" class="formTable">
	    		<tr>
	    			<td>菜单名称:</td>
	    			<td><input class="easyui-validatebox" style="width: 150px" type="text" name="menuName" id="menuName" data-options="required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>配置URL:</td>
	    			<td>
	    				<input class="easyui-validatebox" style="width: 150px" type="text" id="href" name="href">
			    	</td>
	    		</tr>
	    		<tr>
	    			<td>权限:</td>
	    			<td><input class="easyui-combobox" name="roles" id="roles"
	    					data-options="url:'getAllRoles.json',
			                    valueField:'id',
			                    textField:'text',
			                    panelHeight:'auto',
			                    required:true,
			                    multiple:true,
			                    prompt:'下拉选择,可多选'"/></td>
			                    
	    		</tr>
	    		<tr>
	    			<td>menuId:</td>
	    			<td><input class="easyui-numberbox" type="text" name="menuId" id="menuId" data-options="prompt:'只能输入数字，不能重复已有',required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>菜单顺序:</td>
	    			<td><input class="easyui-numberbox" type="text" name="order" id="order" data-options="prompt:'只能输入数字',required:true" /></td>
	    		</tr>
	    		<tr>
	    			<td>图标样式 :</td>
	    			<td><input type="text" name="description" style="width: 150px" id="description" /></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:left;padding:5px 5px 5px 68px;">
	    	<input type="button"  onclick="addMainMenu()" class="btn btn-primary btn-sm" value="保存" />
	    	<a href="javascript:void(0)" onclick="$('#addOrUpdate').dialog('close')" class="btn btn-primary btn-sm">关闭</a>
	    </div>
	    </div>
	</div>

</body>
<script type="text/javascript">

function addMainMenu(){ 
	$.ajax({
		type:"POST",
		data:$('#adminMenuForm').serialize()+"&updateMenuId="+$("#menuId").numberbox('getValue'),
		url:"addOrUpdateAdminMenu.json",
		beforeSend:function(){  
			 var check = $('#adminMenuForm').form('validate');
		       if(check){
		    	   return true;	    	 
		       }else{
		    	   return false;
		    	   
		       }
		    },
		success:function(result){
			if(result.data == 'ADD_SUCCESS'){
				$('#adminMenu').treegrid('append',{
					parent:menuRow.id, 
					data: [{
						id:$("#menuId").val(),
						name:$("#menuName").val(),
						href:$("#href").val(),
						menuId:$("#menuId").val(),
						parentId:$("#parentId").val(),
						order:$("#order").val(),
						roles:$("#roles").combobox('getValues'),
						description:$("#description").val()
					}]
				});
				$('#addOrUpdate').dialog('close');
				$('#adminMenu').treegrid('expand',menuRow.id);//展开父节点
				$.messager.alert('系统提示','保存成功');
				return false;
			}else if(result.data == 'UPDATE_SUCCESS'){
				$('#adminMenu').treegrid('update',{
					id: menuRow.id,
					row: {
						id:$("#menuId").val(),
						name:$("#menuName").val(),
						href:$("#href").val(),
						menuId:$("#menuId").val(),
						parentId:$("#parentId").val(),
						order:$("#order").val(),
						roles:$("#roles").combobox('getValues'),
						description:$("#description").val()
					}
				});
				$.messager.alert('系统提示','修改成功');
				$('#addOrUpdate').dialog('close');
				return false;
			}else if(result.data == 'ADDZHU_SUCCESS'){
				$('#adminMenu').treegrid('append',{
					data: [{
						id:$("#menuId").val(),
						name:$("#menuName").val(),
						href:$("#href").val(),
						menuId:$("#menuId").val(),
						parentId:$("#parentId").val(),
						order:$("#order").val(),
						roles:$("#roles").combobox('getValues'),
						description:$("#description").val()
					}]
				});
				$('#addOrUpdate').dialog('close');
				$.messager.alert('系统提示','保存成功');
				$('#adminMenu').treegrid('reload');
			}else if(result.data == 'MENUID_EXIST'){
				$.messager.alert('系统提示','menuId已存在，请更换');
			}else if(result.data == 'MENU_EXIST'){
				$.messager.alert('系统提示','菜单名已存在，请更换');
			}else{
				$.messager.alert('系统异常','系统异常,请稍后再试');
			}
		}
	});
};  

var menuRow = null;
(function() {
	//初始化模块
	var initObj = {};
	$.extend(initObj, {
		init : function() {
			$('#adminMenu').treegrid({  
				singleSelect:true,
				idField:'id',    
			    treeField:'name', 
			    url:"loadAdminMenuDatas.json",
			    title:"系统菜单管理",
			    fitColumns: true,
			    rownumbers: true,
			    onContextMenu:function(event,row){ //弹出菜单按钮
			    	menuRow = row;
			    	$('#adminMenu').treegrid('select', row.id);
			    	event.preventDefault();
			    	$('#menu_edit').menu('show',{
			    		left: event.pageX,
			    		top: event.pageY
			    	});
			    },
			    columns:[[    
			        {title:'菜单名称',field:'name',width:100},    
			        {field:'href',title:'配置URL',width:110,align:'center'}, 
			        {field:'id',title:'menuId',width:60,align:'center'}, 
			        {field:'parentId',title:'parentId',width:60,align:'center'}, 
			        {field:'order',title:'菜单顺序',width:50,align:'center'},
			        {field:'roles',title:'权限',width:160,align:'center'},
			        {field:'description',title:'图标样式',width:70,align:'center'},
			    ]]    
			});
		}
	});
	
	
	//初始化
	$(document).ready(function() {
		initObj.init();
	});
})();

</script>
</html>