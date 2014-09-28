<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#selectRoles").datagrid(
				{
					title :"已选角色",
					fit:true,
					fitColumns:true,
					loadMsg:'加载中,请稍候...',
					checkbox:true,
					url:"role.do?loadUserSelectRoles&userId="+${userId},
					columns:[[
							  {field:'id',checkbox:true},
					          {field:'name',title:'名称'},
					          {field:'description',title:'描述'}
					      ]],
					tools:[{iconCls:'icon-save',handler:function(){saveSelectRoles();}}],
					
				}
		);
		//保存已选的角色
		function saveSelectRoles() {
			var item = $('#selectRoles').datagrid('getRows');
			console.log(JSON.stringify(item));
			var roleIds = '';
// 			for ( var i = 0; i < item.length; i++) {
// 				roleIds +=
// 			}
			$.post('role.do?saveUserRoles&userId='+${userId},{data:JSON.stringify(item)},function(result){
            	console.log(result);
                if (result.success){
                	$.messager.show({
		                title:'系统提示',
		                msg:'操作成功.',
		                timeout:1000,
		                showType:'slide'
		            });
                } else {
                    $.messager.show({    // show error message
                        title: 'Error',
                        msg: result.msg
                    });
                }
            },'json');
		}
		
		$("#unSelectRoles").datagrid(
				{
					title :"未选角色",
					fit:true,
					fitColumns:true,
					loadMsg:'加载中,请稍候...',
					url:"role.do?loadUserUnSelectRoles&userId="+${userId},
					columns:[[
					          {field:'id',checkbox:true},
					          {field:'name',title:'名称'},
					          {field:'description',title:'描述'}
					      ]]
				}
		);
		//保存已选的角色
		function saveUnSelectRoles() {
// 			alert('unSelectRoles');
			
		}
	});
	//未选->已选
	function rightToLeft(){
		var item = $('#unSelectRoles').datagrid("getSelections");
		console.log(item);
		var removeRows = item.slice(0); 
        if (item) {
            for (var i = item.length - 1; i >= 0; i--) {
                var index = $('#unSelectRoles').datagrid('getRowIndex', item[i]);
                $('#unSelectRoles').datagrid('deleteRow', index);
                console.log(item.length);
            }
			console.log(removeRows);
            for (var j=0;j<removeRows.length;j++) {
            	
            	console.log(JSON.stringify(removeRows[j]));
            	var temp = JSON.stringify(removeRows[j]);
            	$('#selectRoles').datagrid('appendRow',eval(removeRows[j]));
            	console.log(eval(removeRows[j]));
            }
        }
	}
	//已选->未选
	function leftToRight(){
		var item = $('#selectRoles').datagrid("getSelections");
		console.log(item);
		var removeRows = item.slice(0); 
        if (item) {
            for (var i = item.length - 1; i >= 0; i--) {
                var index = $('#selectRoles').datagrid('getRowIndex', item[i]);
                $('#selectRoles').datagrid('deleteRow', index);
                console.log(item.length);
            }
			console.log(removeRows);
            for (var j=0;j<removeRows.length;j++) {
            	
            	console.log(JSON.stringify(removeRows[j]));
            	var temp = JSON.stringify(removeRows[j]);
            	$('#unSelectRoles').datagrid('appendRow',eval(removeRows[j]));
            	console.log(eval(removeRows[j]));
            }
        }
	}
</script>

<table style="border: 0px; width: 100%; height: 100%; overflow: hidden;">
	<tr style="height: 100%; width: 100%;">
		<td style="width: 45%; text-align: center;">
			<table id="selectRoles">

			</table>

		</td>
		<td style="width: 42px; text-align: center;"><input type="button"
			value=">" style="width: 40px;" onclick="leftToRight()" /><br /> <input
			type="button" value=">>" style="width: 40px;" /><br /> <input
			type="button" value="<<"  style=" width:40px;"/><br /> <input
			type="button" value="<"  style=" width:40px;" onclick="rightToLeft()" /><br />

		</td>
		<td style="width: 45%; text-align: center;">

			<table id="unSelectRoles">

			</table>

		</td>

	</tr>
</table>