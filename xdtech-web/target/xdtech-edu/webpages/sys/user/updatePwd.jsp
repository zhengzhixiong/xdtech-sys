<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#updatePwdForm').form({
			url : 'user.do?modifiedPwd',
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
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.handler.dialog('close');
					showMsg(result.msg);
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding:10px 10px 10px 10px">
		<form id="updatePwdForm" method="post">
			<table cellpadding="5">
				<tr>
					<td>旧密码</td>
					<td>
						<input id="oldPwd" name="oldPwd" type="password"  class="easyui-validatebox textbox" data-options="required:true,validType:'length[5,10]'" value="" />
					</td>
				</tr>
				<tr>
					<td>新密码</td>
					<td>
						<input id="newPwd" name="newPwd" type="password" class="easyui-validatebox textbox" data-options="required:true,validType:'length[5,10]'" value="" />
					</td>
				</tr>
				
				<tr>
					<td>密码确认</td>
					<td>
						<input id="confirmPwd" name="confirmPwd" type="password"  class="easyui-validatebox textbox" data-options="required:true,validType:'length[5,10]'" value="" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>