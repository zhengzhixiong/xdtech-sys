<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<%-- <script type="text/javascript" src="<%=basePath%>plugins/xdtech/js/import.js"></script> --%>
<script type="text/javascript">
var roleList;
$(function() {
	roleList = $('#role_rolelist');
	roleList.datagrid({
		title : '角色列表',
		nowrap : false,
		fit : true,
		toolbar : '#role_rolelist_toolbar',
		fitColumns : true,
		rownumbers : true,
		singleSelect : false,
		checkbox : false,
		loadMsg : '加载中,请稍候...',
		queryParams : {
			time : new Date().getTime()
		},
		pagination : true,
		url : 'role.do?loadAllRoles',
		idField : 'id',
		method : 'post',
		sortOrder : 'asc',
		columns : [ [ {
			field : 'id',
			checkbox : true
		}, {
			field : 'code',
			hidden : false,
			title : '角色编码',
			editor : {
				type : 'text'
			},
			sortable : true,
			styler : null,
			width : 100
		}, {
			field : 'name',
			hidden : false,
			title : '角色名称',
			editor : {
				type : 'text'
			},
			sortable : true,
			styler : null,
			width : 100
		}, {
			field : 'superuser',
			hidden : false,
			title : '是否超级管理员',
			formatter: function(value,row,index){
				if (value){
					return "是";
				} else {
					return "否";
				}
			},
			styler : null,
			width : 80
		},{
			field : 'enable',
			hidden : false,
			title : '是否启用',
			formatter: function(value,row,index){
				if (value){
					return "是";
				} else {
					return "否";
				}
			},
			styler : null,
			width : 80
		}, {
			field : 'description',
			hidden : false,
			title : '角色描述',
			sortable : true,
			styler : null,
			width : 100
		},{
			field : 'action',
			title : '操作',
			width : 200,
			formatter : function(value, row, index) {
				var str = '';
				
				str += $.formatString('<img  onclick="rolelist_look(\'{0}\');" src="{1}" title="查看"/>', row.id, 'plugins/xdtech/images/notes/note.png');
				
				str += '&nbsp;';
				
				str += $.formatString('<img  onclick="rolelist_edit(\'{0}\');" src="{1}" title="编辑"/>', row.id, 'plugins/xdtech/images/notes/note_edit.png');
			
				str += '&nbsp;';
				
				str += $.formatString('<img onclick="role_auth(\'{0}\');" src="{1}" title="授权"/>', row.id, 'plugins/xdtech/images/notes/key.png');

				str += '&nbsp;';
				
				str += $.formatString('<img onclick="role_delete(\'{0}\');" src="{1}" title="删除"/>', row.id, 'plugins/xdtech/images/notes/note_delete.png');
				
				
				return str;
			}
		}] ],
		onDbClickRow : null,
	});	
});


function rolelist_remove() {
	var selected = roleList.datagrid('getSelections');
	printLog(selected);
	if(!selected||selected.length==0) {
		alertMsg("请选择删除记录");
	}else {
		var ids = "";
		for(var i=0;i<selected.length;i++){ 
			ids += selected[i].id+","; 
		}
		role_deleteAction(ids);
	}
}

function role_deleteAction(ids) {
	$.messager.confirm('询问', '您是否要删除当前角色？', function(b) {
		if (b) {
			parent.$.messager.progress({
				text : '数据处理中，请稍后....'
			});
			$.post('role.do?deleteRole', {
				ids : ids
			}, function(result) {
				if (result.success) {
					showMsg(result.msg);
//						parent.$.messager.alert('提示', result.msg, 'info');
					$("#role_rolelist").datagrid('reload'); 
				}
				parent.$.messager.progress('close');
			}, 'JSON');
		}
	});
}
function role_auth(id) {
	parent.$.modalDialog({
		title : '角色权限',
		width : 800,
		height : 400,
		href : 'role.do?roleAuth&roleId='+id,
		buttons : [ 
// 		            {
// 			text : '保存',
// 			handler : function() {
// 				parent.$.modalDialog.openner_roleList = roleList;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
// 				var f = parent.$.modalDialog.handler.find('#form');
// 				f.submit();
// 			}
// 		},
		{
			text : '取消',
			handler : function() {
				$.modalDialog.handler.dialog('destroy');
				$.modalDialog.handler = undefined;
// 				$(this).dialog('destroy');
			}
		} ]
	});
}

function role_delete(id) {
	if (id != undefined) {
		roleList.datagrid('selectRecord', id);
	}
	var node = roleList.datagrid('getSelected');
	printLog(node);
	if (node) {
		role_deleteAction(node.id);
	}
}

function rolelist_look(id) {
	parent.$.modalDialog({
		title : '查看角色',
		width : 500,
		height : 300,
		href : 'role.do?addRole&id='+id
	});
}

function rolelist_edit(id) {
	parent.$.modalDialog({
		title : '编辑角色',
		width : 500,
		height : 300,
		href : 'role.do?addRole&id='+id,
		buttons : [ {
			text : '保存',
			handler : function() {
				parent.$.modalDialog.openner_roleList = roleList;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		},{
			text : '取消',
			handler : function() {
				$.modalDialog.handler.dialog('destroy');
				$.modalDialog.handler = undefined;
// 				$(this).dialog('destroy');
			}
		} ]
	});
}

function rolelist_add() {
// 	onloading();
//     $('<div />').window({
//     	title:'添加角色',
//         width:600,
//         height:400,
//         modal:true,
//         href:'role.do?addRole'
//         });
	parent.$.modalDialog({
		title : '添加角色',
		width : 500,
		height : 300,
		href : 'role.do?addRole',
		buttons : [ {
			text : '保存',
			handler : function() {
				parent.$.modalDialog.openner_roleList = roleList;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		},{
			text : '取消',
			handler : function() {
				$.modalDialog.handler.dialog('destroy');
				$.modalDialog.handler = undefined;
// 				$(this).dialog('destroy');
			}
		} ]
	});
}

//刷新
function rolelist_refresh() {
	$("#role_rolelist").datagrid('reload'); 
}

//禁用角色操作
function rolelist_disable() {
	
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<table id="role_rolelist" data-options="fit:true,border:false"></table>

		<div id="role_rolelist_toolbar" style="height: auto">
			<easy:button iconCls="icon-add" onclick="rolelist_add()" labelName="新增" operationCode="111" />
			<easy:button iconCls="icon-remove" onclick="rolelist_remove()" labelName="删除" operationCode="111" />
			<easy:button iconCls="icon-reload" onclick="rolelist_refresh()" labelName="刷新" operationCode="111" />
			
<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton" -->
<!-- 				data-options="iconCls:'icon-add',plain:true" onclick="rolelist_add()">新增</a> -->			
<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton" -->
<!-- 				data-options="iconCls:'icon-remove',plain:true" -->
<!-- 				onclick="rolelist_remove()">删除</a>  -->
<!-- 			<a href="javascript:void(0)" -->
<!-- 				class="easyui-linkbutton" -->
<!-- 				data-options="iconCls:'icon-reload',plain:true" -->
<!-- 				onclick="rolelist_refresh()">刷新</a> -->
		</div>
	</div>
</div>


