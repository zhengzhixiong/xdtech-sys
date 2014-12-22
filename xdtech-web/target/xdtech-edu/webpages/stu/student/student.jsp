<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	var formUrl = "student.do?editStudent";
	function studentlist_add() {
		$('#student_form_id').val("");
		$('#student_win').dialog('open').dialog('setTitle','新增学员');
	    $('#student_form').form('clear');
	}
	
	function studentlist_edit() {
		var row = $('#studentlist').datagrid('getSelected');
		
	    if (row){
	    	printLog(row);
	    	$('#student_form_id').val("");
	        $('#student_win').dialog('open').dialog('setTitle','编辑【'+row.name+'】');
	        $.post("student.do?getGrades", row,
	        	function(data){
	        			printLog(data);
	        			row.grade = eval(data);
	        	 		$('#student_form').form('load',row);
	        	 		$('#student_form_grade_cb').combobox('setValues', eval(data));
	        });
	       
	    }
	}
	
	function studentlist_remove() {
		
	}
	
// 	function studentlist_save() {
		
// 	}

	function saveStudent() {
		$('#student_form').form('submit',{
	        url: formUrl,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	            var result = eval('('+result+')');
	            if (!result.success){
	                $.messager.show({
	                    title: 'Error',
	                    msg: result.msg
	                });
	            } else {
	                $('#student_win').dialog('close');        // close the dialog
	                $('#studentlist').datagrid('reload');    // reload the student data
	            }
	        }
	    });
	}
	
	function studentlist_refresh() {
		 $('#studentlist').datagrid('reload');
	}
	
	function studentlist_search() {
		var params = $('#studentlist').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
		console.log(params);
		var fields =$('#studentlist_search_form').serializeArray(); //自动序列化表单元素为JSON对象  
		printLog(fields);    
	    $.each(fields, function(i, field){  
	        params[field.name] = field.value; //设置查询参数  
	    });  
	    $('#studentlist').datagrid('reload'); //设置好查询参数 reload 一下就可以了  
	}
	
	function studentlist_clear() {
		$('#studentlist_search_form').form('clear');
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<easy:datagrid id="studentlist" url="student.do?loadGridDatas" pagination="true" toolbar="studentlist_search">
			<easy:datagridColumn field="id" />
			<easy:datagridColumn field="no" fieldName="学员编码" editor="{type:'text'}"
				width="100" />
			<easy:datagridColumn field="name" fieldName="姓名" editor="{type:'text'}"
				width="100" />
		</easy:datagrid>
		<div id="studentlist_search">
			<div style="margin-bottom:5px;padding-left:5px;margin-top: 5px;">
				<form id="studentlist_search_form">
					姓名: <input type="text" name="name" style="width: 150px" />
					班级:<easy:comboBox url="grade.do?loadComboBox" width="150" initNullSelect="false" name="grade" id="grade_cb"></easy:comboBox>
						
					 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="studentlist_search()" data-options="plain:true,iconCls:'icon-search'">查询</a>
 					 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="studentlist_clear()" data-options="plain:true,iconCls:'icon-clear'">清除</a>
					 
				</form>
				
				
			</div>
			<easy:toolbar id="studentlist_toolbar">
				<easy:toolbarButton iconCls="icon-add" labelName="新增"
					onclick="studentlist_add()"></easy:toolbarButton>
				<easy:toolbarButton iconCls="icon-edit" labelName="编辑"
					onclick="studentlist_edit()"></easy:toolbarButton>
<%-- 				<easy:toolbarButton iconCls="icon-reload" labelName="刷新" --%>
<%-- 					operationCode="S010204" onclick="studentlist_refresh()"></easy:toolbarButton> --%>
			</easy:toolbar>
		</div>
	</div>
</div>

<!-- 新增和编辑窗口 -->
<div id="student_win" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" data-options="modal:true,resizable:true,maximizable:true,closed:true" buttons="#student_win_buttons">
    <form id="student_form" method="post" novalidate>
        <table cellpadding="5">
        		<input type="hidden" id="student_form_id" name="id"/>
                <tr>
                    <td>学员编号:</td>
                    <td><input class="easyui-validatebox" style="width: 150px" type="text" name="no" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>学员姓名:</td>
                    <td><input class="easyui-validatebox" style="width: 150px" type="text" name="name" data-options="required:true,"></input></td>
                </tr>
                <tr>
                    <td>所属班级:</td>
                    <td>
<!--                     <input class="easyui-combobox"  -->
<!-- 				            name="grade" -->
<!-- 				            data-options=" -->
<!-- 				                    url:'grade.do?loadComboBox', -->
<!-- 				                    method:'get', -->
<!-- 				                    valueField:'code', -->
<!-- 				                    textField:'text', -->
<!-- 				                    multiple:true, -->
<!-- 				                    panelHeight:'auto' -->
<!-- 				            "> -->
                    	<easy:comboBox url="grade.do?loadComboBox" width="154" initNullSelect="false" multiple="true" name="grade" id="student_form_grade_cb"></easy:comboBox>
                    </td>
                </tr>
            </table>
    </form>
</div>
<div id="student_win_buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'"  onclick="saveStudent()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancle'"  onclick="javascript:$('#student_win').dialog('close')">取消</a>
</div>