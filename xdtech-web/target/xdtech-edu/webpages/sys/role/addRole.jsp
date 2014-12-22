<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	$(function() {

// 		$('#iconCls').combobox({
// 			data : $.iconData,
// 			formatter : function(v) {
// 				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
// 			}
// 		});

// 		$('#pid').combotree({
// 			url : '${pageContext.request.contextPath}/resourceController/tree',
// 			parentField : 'pid',
// 			lines : true,
// 			panelHeight : 'auto',
// 			onLoadSuccess : function() {
// 				parent.$.messager.progress('close');
// 			}
// 		});

		$('#form').form({
			url : 'role.do?updateRole',
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
					parent.$.modalDialog.openner_roleList.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<input name="id" type="hidden" value="${role.id }" />
				<tr>
					<td>角色编码</td>
					<td><input name="code" type="text" placeholder="请输入角色编号" class="easyui-validatebox span2" data-options="required:true" value="${role.code }"></td>
				</tr>
				<tr>
					<td>角色名称</td>
					<td><input name="name" type="text" placeholder="请输入角色名称" class="easyui-validatebox span2" data-options="required:true" value="${role.name }"></td>
				</tr>
				<tr>
					<td>是否超级管理员</td>
					<td>
						<c:choose>
							<c:when test="${role.superuser }">
								<input type="checkbox" class="span2" name="superuser" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input type="checkbox" class="span2" name="superuser"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>是否启用</td>
					<td>
						<c:choose>
							<c:when test="${role.enable }">
								<input type="checkbox" class="span2" name="enable" checked="checked"/>
							</c:when>
							<c:otherwise>
								<input type="checkbox" class="span2" name="enable"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>描述</td>
					<td>
						<textarea rows="5" cols="30" name="description"> ${role.description }</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>