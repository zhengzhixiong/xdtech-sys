<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript" src="<%=basePath %>/plugins/xdtech/js/usergroup.js"></script>
<script type="text/javascript">
	function loadUsergroupInfo(node) {
	   var pnode = $('#tree_usergroup').tree('getParent',node.target); 
	   var pGroupId = '';
       var pGroupName= '';
       if (pnode) {
    	   pGroupId = pnode.id;
    	   pGroupName = pnode.text;
       }else {
	   
       }
	   var groupModel = {
			   pGroupId:pGroupId,
			   pGroupName:pGroupName,
		       id:node.id
	   };
	   printLog(groupModel);
	   
	   $.post('group.do?loadUsergroupInfo',{data:JSON.stringify(groupModel)},function(result){
	       	console.log(result);
	           if (result){
	           	$("#usergroupForm").form('load', result);
	           	loadUsergroupRole(result.id);
	           } else {
	        	   showMsg('加载失败.');
	           }
	       },'json');
	}
	
	
	function deleteUserGroup() {
		var node = $("#tree_usergroup").tree("getSelected");
		if (node) {
			 $.messager.confirm('询问','你确认要删除用户吗?',function(r){
	            if (r){
	                $.post('group.do?deleteUsergroup',{id:node.id},function(result){
	                    if (result.success){
	                    	$('#tree_usergroup').tree('reload');  // reload the user data
	                    }
	                    showMsg(result.msg);
	                },'json');
	            }
	        });
		} else {
			showMsg('请选择用户组.');
		}
	}

	function saveUsergroup_usergroup() {
		var actionurl = $('#usergroupForm').attr('action');//提交路径
		var formData = new Object();

		var fields = $('#usergroupForm').serializeArray(); //自动序列化表单元素为JSON对象  

		$.each(fields, function(i, field) {
			//params[field.name] = field.value; //设置查询参数  
			printLog(field.name + "=" + field.value);
			formData[field.name] = field.value;
		});
		printLog(formData);
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : actionurl,// 请求的action路径
			data : formData,
			error : function() {// 请求失败处理函数
				showMsg('保存失败.');
			},
			success : function(data) {
				printLog(data);
				$('#tree_usergroup').tree('reload');
				showMsg('保存成功.');
			}
		});
	}

	function addUsergroupItem() {
		$('#usergroupForm').form('clear');
		var node = $("#tree_usergroup").tree("getSelected");
		console.log(node);
		if (node) {
			var pGroupId = node.id;
			var pGroupName = node.text;
			var group = {
				pGroupId : pGroupId,
				pGroupName : pGroupName,
				name : '',
				description : ''
			};

			printLog(group)
			$("#usergroupForm").form('load', group);
		} else {
			showMsg('请选择用户组.');
		}
	}
	function refreshGroup() {
		$('#tree_usergroup').tree('reload');
		
	}

	function loadUsergroupRole(usergroupId) {
		var tab = $('#tabs_usergroup').tabs('getSelected');
		var index = $('#tabs_usergroup').tabs('getTabIndex', tab);
		printLog(index);
		switch (index) {
		case 0:
			tab.panel('refresh', "group.do?toUsergroupRole&usergroupId="
					+ usergroupId);
			break;
		case 1:
			//tab.panel("refresh", "user.do?usergroupRoleList&userId=" + userId);
			break;
		case 2:
			//tab.panel("refresh", "user.do?userRoleList&userId=" + userId);
			break;
		default:
			tab.panel('refresh', "group.do?toUsergroupRole&usergroupId="
					+ usergroupId);
			break;
		}

	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'west',split:true,title:'用户组',collapsible:false,border:false,tools:[
				{
					iconCls:'icon-reload',
					handler:function(){refreshGroup()}
				}]" style="width:200px;">
           <ul id="tree_usergroup" class="easyui-tree" data-options="
		          url: '<%=basePath %>group.do?usergroupTree',
		          method: 'get',
		          animate: true,
		          dnd:true,
		          lines:true,
		          formatter:function(node){
		                    var s = node.text;
		                    if (node.children.length>0){
		                        s += '&nbsp;<span style=\'color:blue\'>' + node.children.length + '</span>';
		                    }
		                    return s;
		          },
		          onClick: function(node){
		          		console.log(node);
		          		loadUsergroupInfo(node);
		                //$(this).tree('beginEdit',node.target);
		          }
		      ">
		  </ul>
        </div>
        <div data-options="region:'center',border:false,iconCls:'icon-ok'">
		<div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'north',border:false" style="height:62px;overflow: hidden;">
       			<easy:toolbar id="toolbar_usergroup" style="border:1px solid #ddd;">
		       		<easy:toolbarButton iconCls="icon-add" labelName="新增" onclick="addUsergroupItem()"></easy:toolbarButton>
		       		<easy:toolbarButton iconCls="icon-remove" labelName="删除" onclick="deleteUserGroup()"></easy:toolbarButton>
		       		<easy:toolbarButton iconCls="icon-save" labelName="保存" onclick="saveUsergroup_usergroup()"></easy:toolbarButton>
		       	</easy:toolbar>
			    <form id="usergroupForm" action="group.do?editUsergroup">
			    	<input type="hidden" name="pGroupId"/>
			    	<input type="hidden" name="id"/>
			    	<table>
			    		<tr>
			    			<td>上级组别:</td>
			    			<td><input type="text" name="pGroupName" disabled="disabled"></input></td>
			    			<td>组别名称:</td>
			    			<td><input type="text" name="name" ></input></td>
			    			<td>描述:</td>
			    			<td><input type="text" name="description" ></input></td>
			    		</tr>
			    	</table>
			    </form>
       		</div>
            <div data-options="region:'center',border:false">
					<div class="easyui-tabs" id="tabs_usergroup" data-options="fit:true,border:false">  
			            <div id="usergroup_role_usergroup" title="用户组-角色" data-options="fit:true,border:false" style="overflow:auto;">   
			            
			            </div>  
			        </div> 
            </div>
        </div>
		
</div>
</div>
    