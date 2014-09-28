<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#selectRoles_usergroupRole").datagrid(
				{
					title :"已选角色",
					fit:true,
					fitColumns:true,
					loadMsg:'加载中,请稍候...',
					checkbox:true,
					url:"role.do?loadGroupSelectRoles&usergroupId="+${usergroupId},
					columns:[[
							  {field:'id',checkbox:true},
					          {field:'name',title:'名称'},
					          {field:'description',title:'描述'}
					      ]],
					tools:[{iconCls:'icon-save',handler:function(){saveUsergroupSelectRoles();}}],
					
				}
		);
		//保存已选的角色
		function saveUsergroupSelectRoles() {
			var item = $('#selectRoles_usergroupRole').datagrid('getRows');
			printLog(JSON.stringify(item));
			var roleIds = '';

			$.post('role.do?saveUsergroupSelectRoles&usergroupId='+${usergroupId},{data:JSON.stringify(item)},function(result){
            	printLog(result);
                if (result.success){
                	showSucMsg();
                } else {
                	showFailMsg();
                }
            },'json');
		}
		
		$("#unSelectRoles_usergroupRole").datagrid(
				{
					title :"未选角色",
					fit:true,
					fitColumns:true,
					loadMsg:'加载中,请稍候...',
					url:"role.do?loadGroupUnSelectRoles&usergroupId="+${usergroupId},
					columns:[[
					          {field:'id',checkbox:true},
					          {field:'name',title:'名称'},
					          {field:'description',title:'描述'}
					      ]]
				}
		);
	});
	//未选->已选
	function group_rightToLeft(){
		var item = $('#unSelectRoles_usergroupRole').datagrid("getSelections");
		printLog(item);
		var removeRows = item.slice(0); 
        if (item) {
            for (var i = item.length - 1; i >= 0; i--) {
                var index = $('#unSelectRoles_usergroupRole').datagrid('getRowIndex', item[i]);
                $('#unSelectRoles_usergroupRole').datagrid('deleteRow', index);
                printLog(item.length);
            }
			printLog(removeRows);
            for (var j=0;j<removeRows.length;j++) {
            	
            	printLog(JSON.stringify(removeRows[j]));
            	var temp = JSON.stringify(removeRows[j]);
            	$('#selectRoles_usergroupRole').datagrid('appendRow',eval(removeRows[j]));
            	printLog(eval(removeRows[j]));
            }
        }
	}
	//已选->未选
	function group_leftToRight(){
		var item = $('#selectRoles_usergroupRole').datagrid("getSelections");
		printLog(item);
		var removeRows = item.slice(0); 
        if (item) {
            for (var i = item.length - 1; i >= 0; i--) {
                var index = $('#selectRoles_usergroupRole').datagrid('getRowIndex', item[i]);
                $('#selectRoles_usergroupRole').datagrid('deleteRow', index);
                printLog(item.length);
            }
			printLog(removeRows);
            for (var j=0;j<removeRows.length;j++) {
            	
            	printLog(JSON.stringify(removeRows[j]));
            	var temp = JSON.stringify(removeRows[j]);
            	$('#unSelectRoles_usergroupRole').datagrid('appendRow',eval(removeRows[j]));
            	printLog(eval(removeRows[j]));
            }
        }
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region: 'west',width:400,border:false" style="padding:1px 1px;">
		<table id="selectRoles_usergroupRole" >    
		</table>  
	</div>
	
	<div data-options="region:'center',width:42,border:false">
		<div  style="text-align: center;margin-top: 200px">
			<input type="button" value=">" style="width: 40px;"
				onclick="group_leftToRight()" /><br /> 
	
				
			<input type="button"
				value="<"  style=" width:40px;" onclick="group_rightToLeft()" />
		</div>
	</div>
	
	<div data-options="region:'east',width:400,border:false" style="padding:1px 1px;">
		 <table id="unSelectRoles_usergroupRole">    
		 </table>
	</div>

</div>