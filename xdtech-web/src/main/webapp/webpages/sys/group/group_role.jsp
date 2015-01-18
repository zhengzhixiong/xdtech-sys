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
<table style="width:100%;height: 100%;">
  <tr>
    <td style="width:45%;height: 100%;padding: 2px 2px;">
<!-- 		<div class="easyui-layout" data-options="border:false"> -->
<!-- 			<div data-options="region:'center',border:false"> -->
				<table id="selectRoles_usergroupRole" >    
				</table>  
<!-- 			</div> -->
<!-- 		</div> -->
	</td>
    <td style="height: 100%;text-align: center;">
    	<div style="padding: 2px 2px;">
    		<input type="button" value=">" style="width: 40px;"
				onclick="group_leftToRight()" /><br /> 
	
				
			<input type="button" value="<" style="width: 40px;" onclick="group_rightToLeft()" />
    	</div>
		
	</td>
    <td style="width:45%;height: 100%;padding: 2px 2px;">
<!--     	<div class="easyui-layout" data-options="border:false"> -->
<!-- 			<div data-options="region:'center',border:false"> -->
				 <table id="unSelectRoles_usergroupRole">    
		 		 </table>
<!-- 			</div> -->
<!-- 		</div> -->
	</td>
  </tr>
</table>