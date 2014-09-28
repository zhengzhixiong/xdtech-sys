<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<%
	String treeId = "roleOperationTree"+request.getParameter("roleId");
	String treeIdFlag = "#"+treeId;
%>
<script type="text/javascript">
	var menuNodeId;
   function roleOperationSave(roleId) {
	   printLog('保存角色权限菜单'+roleId);
	   var nodes = $('#roleOperationTree'+roleId).tree('getChecked');
	   printLog(nodes);
	   var selects = [];
	   for (var i = 0; i < nodes.length; i++) {
			var item = nodes[i];
			var pItem = item.attributes;
			if(pItem) {
				selects.push({id:item.id,pid:pItem.id});
			}else {
				selects.push({id:item.id,pid:-1});
			}
			
	   }
  	   printLog(nodes);
  	   printLog(JSON.stringify(selects));
	   $.post("role.do?updateRoleOperations&roleId="+roleId, {data:JSON.stringify(selects)}, function(result) {
	         if(result.success){
	        	$('#roleOperationTree'+roleId).tree('reload'); 
	      	    showMsg('保存成功.');
	         }else {
	      	   showMsg('保存失败.');
	         }
		 }, "JSON");
   }
   //保存角色按钮集合
   function buttonlist_role_save(roleId) {
	   var item = $('#buttonOperationsList'+roleId).datagrid("getSelections");
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
   //加载菜单下按钮
   function loadMenuButtonList(node,roleId) {
	   var isLeaf = $('#roleOperationTree'+roleId).tree('isLeaf',node.target);
	   if(isLeaf) {
		   menuNodeId = node.id;
		   //判读是否是叶子结点
		   var params = $('#buttonOperationsList'+roleId).datagrid('options').queryParams; //先取得 datagrid 的查询参数  
		   printLog(params);
		   if (node) {
			 params['menuId'] = node.id;
			 params['roleId'] = roleId;
		   }
		   printLog(params);
		   $('#buttonOperationsList'+roleId).datagrid('reload'); 
		   //设置好查询参数 reload 一下就可以了  
	   }
   }
   
   function roleButtonClick(index,data) {
	   printLog(data);
   }
</script>
<div class="easyui-layout" data-options="fit:true">
	 <div data-options="region:'west',split:true,title:'功能菜单',collapsible:false,border:false,tools:[
				{
					iconCls:'icon-save',
					handler:function(){roleOperationSave(${roleId })}
				}]" style="width:200px;">
	 	<ul id="${roleOperationTree }" class="easyui-tree" data-options="
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
        <easy:datagrid id="${grid }" url="operation.do?loadByMenuIdAndRoleId" checkbox="true" onClickRow="roleButtonClick(index,data);"   pagination="false" singleSelect="false" toolbar="${toolbar }">
        	<easy:datagridColumn field="id"/>
        	<easy:datagridColumn field="menuFunId" hidden="true"/>
        	<easy:datagridColumn field="type" hidden="true"/>
        	<easy:datagridColumn field="operationCode" fieldName="权限编码"  width="100"/>
        	<easy:datagridColumn field="labelName" fieldName="显示名称" width="100"/>
        	<easy:datagridColumn field="iconName" fieldName="图标" width="80"/>
        	<easy:datagridColumn field="description" fieldName="描述" width="400"/>
        </easy:datagrid>
		<easy:toolbar id="${toolbar }">
       		<easy:toolbarButton iconCls="icon-save" labelName="保存" onclick="buttonlist_role_save(${roleId })"></easy:toolbarButton>
       	</easy:toolbar>
     </div>
</div>
