<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
$.extend($.fn.datagrid.methods, {
    editCell: function(jq,param){
        return jq.each(function(){
            var opts = $(this).datagrid('options');
            var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor1 = col.editor;
                if (fields[i] != param.field){
                    col.editor = null;
                }
            }
            $(this).datagrid('beginEdit', param.index);
            for(var i=0; i<fields.length; i++){
                var col = $(this).datagrid('getColumnOption', fields[i]);
                col.editor = col.editor1;
            }
        });
    }
});

var editIndex_scorelist = undefined;
function endEditing_scorelist() {
	if (editIndex_scorelist == undefined) {
		return true
	}
	if ($('#scorelist').datagrid('validateRow', editIndex_scorelist)) {
		var ed = $('#scorelist').datagrid('getEditor', {
			index : editIndex_scorelist,
			field : 'code'
		});
		$('#scorelist').datagrid('endEdit', editIndex_scorelist);
		editIndex_scorelist = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow_scorelist(index, data) {
	if (editIndex_scorelist != index) {
		if (endEditing_scorelist()) {
			$('#scorelist').datagrid('selectRow', index).datagrid(
					'beginEdit', index);
			editIndex_scorelist = index;
		} else {
			$('#scorelist').datagrid('selectRow', editIndex_scorelist);
		}
	}
}
function scorelist_add() {
	if (endEditing_scorelist()) {
		$('#scorelist').datagrid('appendRow', {});
		editIndex_scorelist = $('#scorelist').datagrid('getRows').length - 1;
		$('#scorelist').datagrid('selectRow', editIndex_scorelist)
				.datagrid('beginEdit', editIndex_scorelist);
	}
}
function scorelist_remove() {
	if (editIndex_scorelist == undefined) {
		return
	}
	$('#scorelist').datagrid('cancelEdit', editIndex_scorelist).datagrid(
			'deleteRow', editIndex_scorelist);
	editIndex_scorelist = undefined;
}
function scorelist_save() {
	if (endEditing_scorelist()) {
		var inserted = $('#scorelist').datagrid('getChanges', "inserted");
		var deleted = $('#scorelist').datagrid('getChanges', "deleted");
		var updated = $('#scorelist').datagrid('getChanges', "updated");

		var effectRow = {
			inserted : inserted,
			deleted : deleted,
			updated : updated
		};
		printLog(effectRow);
		printLog(JSON.stringify(effectRow));
		
		$.post("score.do?saveOrUpdate", {
			data : JSON.stringify(effectRow)
		}, function(result) {
			if (result.success) {
				showMsg('保存成功.');
				$("#scorelist").datagrid('acceptChanges');
			} else {
				showMsg('保存失败.');
			}
		}, "JSON");
	}
}

function scorelist_refresh() {
	$("#scorelist").datagrid('reload'); 
// 	scorelist_search();
}

function scorelist_search() {
	if($('#scorelist_search_form_grade_cb').combobox('getValue')&&$('#scorelist_search_form_subject_cb').combobox('getValue')) {
// 		var title = $('#scorelist_search_form_grade_cb').combobox('getText')+'-'+$('#scorelist_search_form_subject_cb').combobox('getText')+'成绩录入';
		 
		var fields =$('#scorelist_search_form').serializeArray(); //自动序列化表单元素为JSON对象  
// 		printLog(fields);
		var params = $('#scorelist').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
	    $.each(fields, function(i, field){  
	        params[field.name] = field.value; //设置查询参数  
	    });  
	    printLog(params);
	    $("#scorelist").datagrid('reload'); 
	}else {
		alertMsg('请选择班级和科目.');
	}
}

function scorelist_clear() {
	
	$('#scorelist_search_form').form('clear');
	set_scorelist_params();
	$("#scorelist").datagrid('reload'); 
// 	$("#scorelist").datagrid({title:''});
}
function set_scorelist_params() {
	var fields =$('#scorelist_search_form').serializeArray(); //自动序列化表单元素为JSON对象  
	var params = $('#scorelist').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    $.each(fields, function(i, field){  
    	delete params[field.name];
//         params[field.name] = field.value; //设置查询参数  
    });  
    printLog(params);
}
function scorelist_entering() {
// 	$('#com').combobox('getValue')获取当前选中的值 $('#com').combobox('getText')获取当前选中的文字
	if($('#scorelist_search_form_grade_cb').combobox('getValue')&&$('#scorelist_search_form_subject_cb').combobox('getValue')) {
		var title = $('#scorelist_search_form_grade_cb').combobox('getText')+'-'+$('#scorelist_search_form_subject_cb').combobox('getText')+'成绩录入';
		 
		var fields =$('#scorelist_search_form').serializeArray(); //自动序列化表单元素为JSON对象  
		printLog(fields);
		var params = $('#scorelist').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
	    $.each(fields, function(i, field){  
	        params[field.name] = field.value; //设置查询参数  
	    });  
	    $("#scorelist").datagrid({title:title}); 
// 		$("#scorelist").datagrid({url:'score.do?loadEnteringInfo'});
// 	    $('#scorelist').datagrid('reload'); //设置好查询参数 reload 一下就可以了  
		
	}else {
		alertMsg('请选择班级和科目.');
	}
	
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<easy:datagrid id="scorelist" url="score.do?loadList"
			pagination="false" onClickRow="onClickRow_scorelist(index,data);" 
			toolbar="scorelist_search" >
			<easy:datagridColumn field="id" />
			<easy:datagridColumn field="studentNo" fieldName="学员编号"  width="60" sortable="false"/>
			<easy:datagridColumn field="studentName" fieldName="学员姓名" width="60" sortable="false"/>
			<easy:datagridColumn field="point" fieldName="成绩" editor="{type:'numberbox',options:{precision:2}}" width="60" styler="function(value,row,index){if (value < 60){return 'color:red;';}}"/>
		</easy:datagrid>
		<div id="scorelist_search">
			<div style="margin-bottom:5px;padding-left:5px;margin-top: 5px;">
				<form id="scorelist_search_form">
					班级:
<!-- 					<input class="easyui-combobox"  name="gradeId" id="scorelist_search_form_grade_cb" -->
<!-- 				            data-options=" url:'grade.do?loadComboBox', -->
<!-- 				                    method:'get', -->
<!-- 				                    width:150, -->
<!-- 				                    valueField:'code', -->
<!-- 				                    textField:'text', -->
<!-- 				                    multiple:false, -->
<!-- 				                    panelHeight:'auto' " /> -->
					<easy:comboBox url="grade.do?loadComboBox" width="150" initNullSelect="false" name="gradeId" id="scorelist_search_form_grade_cb"></easy:comboBox>

					科目:
					<easy:comboBox url="subject.do?loadComboBox" width="150" initNullSelect="false" name="subjectId" id="scorelist_search_form_subject_cb"></easy:comboBox>
					
<!-- 					<input class="easyui-combobox"  name="subjectId" id="scorelist_search_form_subject_cb" -->
<!-- 				            data-options=" url:'subject.do?loadComboBox', -->
<!-- 				                    method:'get', -->
<!-- 				                    width:150, -->
<!-- 				                    valueField:'code', -->
<!-- 				                    textField:'text', -->
<!-- 				                    multiple:false, -->
<!-- 				                    panelHeight:'auto' " /> -->
					
						
					 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="scorelist_search()" data-options="plain:true,iconCls:'icon-search'">查询</a>
<!--  					 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="scorelist_entering()" data-options="plain:true,iconCls:'icon-search'">录入</a> -->
 					 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="scorelist_clear()" data-options="plain:true,iconCls:'icon-clear'">清除</a>
					 
				</form>
			</div>
			<easy:toolbar id="scorelist_toolbar">
				<easy:toolbarButton iconCls="icon-save" labelName="保存"
					operationCode="S010204" onclick="scorelist_save()" />			
				<easy:toolbarButton iconCls="icon-import" labelName="导入"
 					onclick="scorelist_add()"/> 
				<easy:toolbarButton iconCls="icon-export" labelName="导出"
 					onclick="scorelist_remove()"/> 
				<easy:toolbarButton iconCls="icon-download" labelName="下载模板"
 					onclick="scorelist_remove()"/> 
 				<easy:toolbarButton iconCls="icon-send" labelName="发送"
 					onclick="scorelist_send()"/> 
				<easy:toolbarButton iconCls="icon-reload" labelName="刷新"
 					operationCode="S010204" onclick="scorelist_refresh()"/> 
			</easy:toolbar>
			
			
		</div>
		
	</div>
	
</div>