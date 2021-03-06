<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#form').form({
			url : 'user.do?editUser',
			onSubmit : function() {
				parent.$.messager.progress({
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				printLog(result);
				result = $.parseJSON(result);
				
				if (result.success) {
					parent.$.modalDialog.openner_userList.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					showMsg(result.msg);
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding:10px 10px 10px 10px">
		<form id="form" method="post">
			<table cellpadding="5">
				<input name="id" type="hidden" value="${userItem.id }" />
				<tr>
					<td>用户名</td>
					<td>
						<input name="loginName"  class="easyui-validatebox textbox" data-options="required:true,validType:'length[3,10]'" value="${userItem.loginName }" />
					</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>
						<input name="name"  class="easyui-validatebox textbox" value="${userItem.name }" />
					</td>
				</tr>
				
				<tr>
					<td>性别</td>
					<td>
						<select class="easyui-combobox" name="sex" style="width:153px;">
							<option value="M" selected='<c:if test="userItem.sex=='M'">selected</c:if>'>男</option>
					        <option value="F" selected='<c:if test="userItem.sex=='F'">selected</c:if>'>女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>用户组</td>
					<td>
<!-- 					        <input type="checkbox" checked onclick="$('#cc').combotree({cascadeCheck:$(this).is(':checked')})"> -->
						<easy:comboTree url="group.do?usergroupTree&userId=${userItem.id}" multiple="true" name="groupIds"/>
<!-- 						<select id="cc" class="easyui-combotree" style="width:153px;" data-options="url:'group.do?usergroupTree',required:true"></select> -->
<!-- 						<select name="groupIds" class="easyui-combotree" data-options="url:'group.do?usergroupTree,required:true',method:'get'" multiple style="width:153px;"></select> -->
						
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>