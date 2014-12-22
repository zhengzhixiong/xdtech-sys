<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	var menuNodeId;
	
	var roleButtonOperationList;
	$(function() {
		roleButtonOperationList = $('#buttonOperationsList');
		roleButtonOperationList.datagrid({
			nowrap : false,
			title:'权限按钮列表',
			fit : true,
			toolbar : '#role_btnOperation_toolbar',
			fitColumns : true,
			rownumbers : true,
			singleSelect : false,
			checkbox : false,
			loadMsg : '加载中,请稍候...',
			queryParams : {
				time : new Date().getTime()
			},
			pagination : false,
			url : 'operation.do?loadByMenuIdAndRoleId',
			idField : 'id',
			method : 'post',
			sortOrder : 'asc',
			columns : [ [ {
				field : 'id',
				checkbox : true
			}, {
				field : 'menuFunId',
				hidden : true,
				sortable : true,
				styler : null,
				width : 100
			}, {
				field : 'type',
				hidden : true,
				sortable : true,
				styler : null,
				width : 100
			}, {
				field : 'operationCode',
				hidden : false,
				title : '权限编码',
				sortable : true,
				styler : null,
				width : 80
			}, {
				field : 'labelName',
				hidden : false,
				title : '显示名称',
				sortable : true,
				styler : null,
				width : 100
			},{
				field : 'iconName',
				hidden : false,
				title : '图标',
				sortable : true,
				styler : null,
				width : 100
			},{
				field : 'description',
				hidden : false,
				title : '描述',
				sortable : true,
				styler : null,
				width : 100
			}] ],
			onDbClickRow : null,
		});	
		
	});

	function roleOperationSave(roleId) {
		printLog('保存角色权限菜单' + roleId);
		var nodes = $('#roleOperationTree').tree('getChecked');
		printLog(nodes);
		var selects = [];
		for ( var i = 0; i < nodes.length; i++) {
			var item = nodes[i];
			var pItem = item.attributes;
			if (pItem) {
				selects.push({
					id : item.id,
					pid : pItem.id
				});
			} else {
				selects.push({
					id : item.id,
					pid : -1
				});
			}

		}
		printLog(nodes);
		printLog(JSON.stringify(selects));
		$.post("role.do?updateRoleOperations&roleId=" + roleId, {
			data : JSON.stringify(selects)
		}, function(result) {
			if (result.success) {
				$('#roleOperationTree').tree('reload');
				showMsg('保存成功.');
			} else {
				showMsg('保存失败.');
			}
		}, "JSON");
	}
	//保存角色按钮集合
	function buttonlist_role_save(roleId) {
		var item = $('#buttonOperationsList')
				.datagrid("getSelections");
		printLog(JSON.stringify(item));
		$.post('operation.do?saveRoleButtons&roleId=${roleId}&menuId='
				+ menuNodeId, {
			data : JSON.stringify(item)
		}, function(result) {
			console.log(result);
			if (result.success) {
				showMsg('操作成功.');
			} else {
				showMsg('操作失败.');
			}
		}, 'json');
	}
	//加载菜单下按钮
	function loadMenuButtonList(node, roleId) {
		var isLeaf = $('#roleOperationTree').tree('isLeaf',
				node.target);
		if (isLeaf) {
			menuNodeId = node.id;
			//判读是否是叶子结点
			var params = $('#buttonOperationsList')
					.datagrid('options').queryParams; //先取得 datagrid 的查询参数  
			printLog(params);
			if (node) {
				params['menuId'] = node.id;
				params['roleId'] = roleId;
			}
			printLog(params);
			$('#buttonOperationsList').datagrid('reload');
			//设置好查询参数 reload 一下就可以了  
		}
	}

	function roleButtonClick(index, data) {
		printLog(data);
	}
	
	function operationBtn_refresh() {
		
	}
	
	function operationBtn_save(roleId) {
		var item = $('#buttonOperationsList').datagrid("getSelections");
		   printLog(JSON.stringify(item));
		   $.post('operation.do?saveRoleButtons&roleId='+roleId+"&menuId="+menuNodeId,{data:JSON.stringify(item)},function(result){
	       	console.log(result);
	           if (result.success){
	            	showMsg('操作成功.');
	           } else {
	        	   showMsg('操作失败.');
	           }
	       },'json');
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div
		data-options="region:'west',split:true,title:'权限菜单',collapsible:false,border:false,tools:[
				{
					iconCls:'icon-save',
					handler:function(){roleOperationSave(${roleId })}
				}]"
		style="width: 200px;">
		<ul id="roleOperationTree" class="easyui-tree"
			data-options="
		          url: '<%=basePath %>role.do?menuTree&roleId='+${roleId },
		          method: 'post',
		          animate: true,
		          lines:true,
		          checkbox:true,
		          onClick:function(node){
		          		printLog(node);
						loadMenuButtonList(node,${roleId });
				  }">
		</ul>
	</div>
	
	<div data-options="region:'center',border:false">
		<table id="buttonOperationsList" data-options="fit:true,border:false"></table>
		
		<div id="role_btnOperation_toolbar" style="height: auto">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save',plain:true" onclick="operationBtn_save('${roleId}')">保存</a>				
			<a href="javascript:void(0)"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-reload',plain:true"
				onclick="operationBtn_refresh()">刷新</a>
		</div>
	</div>
</div>