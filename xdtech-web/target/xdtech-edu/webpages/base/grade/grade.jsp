<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	var editIndex_gradelist = undefined;
	function endEditing_gradelist() {
		if (editIndex_gradelist == undefined) {
			return true;
		}
		if ($('#gradelist').datagrid('validateRow', editIndex_gradelist)) {
			var ed = $('#gradelist').datagrid('getEditor', {
				index : editIndex_gradelist,
				field : 'code'
			});
			$('#gradelist').datagrid('endEdit', editIndex_gradelist);
			editIndex_gradelist = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow_gradelist(index, data) {
		if (editIndex_gradelist != index) {
			if (endEditing_gradelist()) {
				$('#gradelist').datagrid('selectRow', index).datagrid(
						'beginEdit', index);
				editIndex_gradelist = index;
			} else {
				$('#gradelist').datagrid('selectRow', editIndex_gradelist);
			}
		}
	}
	function gradelist_add() {
		if (endEditing_gradelist()) {
			$('#gradelist').datagrid('appendRow', {});
			editIndex_gradelist = $('#gradelist').datagrid('getRows').length - 1;
			$('#gradelist').datagrid('selectRow', editIndex_gradelist)
					.datagrid('beginEdit', editIndex_gradelist);
		}
	}
	function gradelist_remove() {
		if (editIndex_gradelist == undefined) {
			return
		}
		$('#gradelist').datagrid('cancelEdit', editIndex_gradelist).datagrid(
				'deleteRow', editIndex_gradelist);
		editIndex_gradelist = undefined;
	}
	function gradelist_save() {
		if (endEditing_gradelist()) {
			var inserted = $('#gradelist').datagrid('getChanges', "inserted");
			var deleted = $('#gradelist').datagrid('getChanges', "deleted");
			var updated = $('#gradelist').datagrid('getChanges', "updated");

			var effectRow = {
				inserted : inserted,
				deleted : deleted,
				updated : updated
			};
			printLog(effectRow);
			printLog(JSON.stringify(effectRow));
			
			$.post("grade.do?saveOrUpdate", {
				data : JSON.stringify(effectRow)
			}, function(result) {
				if (result.success) {
					showMsg('保存成功.');
					$("#gradelist").datagrid('acceptChanges');
				} else {
					showMsg('保存失败.');
				}
			}, "JSON");
		}
	}
	
	function gradelist_refresh() {
		$("#gradelist").datagrid('reload'); 
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<easy:datagrid id="gradelist" title="班级列表" url="grade.do?loadList"
			pagination="false" onClickRow="onClickRow_gradelist(index,data);"
			toolbar="gradelist_toolbar">
			<easy:datagridColumn field="id" />
			<easy:datagridColumn field="code" fieldName="班级编码" editor="{type:'text'}"
				width="100" />
			<easy:datagridColumn field="name" fieldName="班级名称" editor="{type:'text'}"
				width="100" />
		</easy:datagrid>
		<easy:toolbar id="gradelist_toolbar">
			<easy:toolbarButton iconCls="icon-add" labelName="新增"
				onclick="gradelist_add()"></easy:toolbarButton>
			<easy:toolbarButton iconCls="icon-remove" labelName="移除"
				onclick="gradelist_remove()"></easy:toolbarButton>
			<easy:toolbarButton iconCls="icon-save" labelName="保存"
				operationCode="S010204" onclick="gradelist_save()"></easy:toolbarButton>
			<easy:toolbarButton iconCls="icon-reload" labelName="刷新"
				operationCode="S010204" onclick="gradelist_refresh()"></easy:toolbarButton>
		</easy:toolbar>
	</div>
</div>