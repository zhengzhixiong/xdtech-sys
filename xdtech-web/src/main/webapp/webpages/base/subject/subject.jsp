<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	var editIndex_subjectlist = undefined;
	function endEditing_subjectlist() {
		if (editIndex_subjectlist == undefined) {
			return true
		}
		if ($('#subjectlist').datagrid('validateRow', editIndex_subjectlist)) {
			var ed = $('#subjectlist').datagrid('getEditor', {
				index : editIndex_subjectlist,
				field : 'code'
			});
			$('#subjectlist').datagrid('endEdit', editIndex_subjectlist);
			editIndex_subjectlist = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow_subjectlist(index, data) {
		if (editIndex_subjectlist != index) {
			if (endEditing_subjectlist()) {
				$('#subjectlist').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				editIndex_subjectlist = index;
			} else {
				$('#subjectlist').datagrid('selectRow', editIndex_subjectlist);
			}
		}
	}
	function subjectlist_add() {
		if (endEditing_subjectlist()) {
			$('#subjectlist').datagrid('appendRow', {});
			editIndex_subjectlist = $('#subjectlist').datagrid('getRows').length - 1;
			$('#subjectlist').datagrid('selectRow', editIndex_subjectlist)
					.datagrid('beginEdit', editIndex_subjectlist);
		}
	}
	function subjectlist_remove() {
		if (editIndex_subjectlist == undefined) {
			return
		}
		$('#subjectlist').datagrid('cancelEdit', editIndex_subjectlist).datagrid(
				'deleteRow', editIndex_subjectlist);
		editIndex_subjectlist = undefined;
	}
	function subjectlist_save() {
		if (endEditing_subjectlist()) {
			var inserted = $('#subjectlist').datagrid('getChanges', "inserted");
			var deleted = $('#subjectlist').datagrid('getChanges', "deleted");
			var updated = $('#subjectlist').datagrid('getChanges', "updated");

			var effectRow = {
				inserted : inserted,
				deleted : deleted,
				updated : updated
			};
			printLog(effectRow);
			printLog(JSON.stringify(effectRow));
			
			$.post("subject.do?saveOrUpdate", {
				data : JSON.stringify(effectRow)
			}, function(result) {
				if (result.success) {
					showMsg('保存成功.');
					$("#subjectlist").datagrid('acceptChanges');
				} else {
					showMsg('保存失败.');
				}
			}, "JSON");
		}
	}
	
	function subjectlist_refresh() {
		$("#subjectlist").datagrid('reload'); 
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<easy:datagrid id="subjectlist" title="课程列表" url="subject.do?loadList"
			pagination="false" onClickRow="onClickRow_subjectlist(index,data);"
			toolbar="subjectlist_toolbar">
			<easy:datagridColumn field="id" />
			<easy:datagridColumn field="code" fieldName="课程编码" editor="{type:'text'}"
				width="100" />
			<easy:datagridColumn field="name" fieldName="课程名称" editor="{type:'text'}"
				width="100" />
		</easy:datagrid>
		<easy:toolbar id="subjectlist_toolbar">
			<easy:toolbarButton iconCls="icon-add" labelName="新增"
				onclick="subjectlist_add()"></easy:toolbarButton>
			<easy:toolbarButton iconCls="icon-remove" labelName="移除"
				onclick="subjectlist_remove()"></easy:toolbarButton>
			<easy:toolbarButton iconCls="icon-save" labelName="保存"
				operationCode="S010204" onclick="subjectlist_save()"></easy:toolbarButton>
			<easy:toolbarButton iconCls="icon-reload" labelName="刷新"
				operationCode="S010204" onclick="subjectlist_refresh()"></easy:toolbarButton>
		</easy:toolbar>
	</div>
</div>