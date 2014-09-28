<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script>
	//左侧用户组树选中标记
	var usergroupId = null;
	//用户列表
	var userList = null;
	//查询的用户姓名
	var name = null;
	$(function() {
		userList = $('#user_userlist');
		userList.datagrid({
			title : '用户列表',
			nowrap : false,
			fit : true,
			toolbar : '#userlist_search',
			fitColumns : true,
			rownumbers : true,
			singleSelect : false,
			checkbox : false,
			loadMsg : '加载中,请稍候...',
			queryParams : {
				time : new Date().getTime()
			},
			pagination : true,
			url : 'user.do?loadByCondition',
			idField : 'id',
			method : 'post',
			pageList : [30, 50, 100, 200, 500],
			columns : [ [ {
				field : 'id',
				checkbox : true
			}, {
				field : 'loginName',
				title : '用户名',
				width : 100
			}, {
				field : 'name',
				title : '姓名',
				width : 100
			}, {
				field : 'sex',
				title : '性别',
				width : 50,
				formatter : function(value, row, index) {
					if (value=='M'){
						return "男";
					} else if (value=='F') {
						return "女";
					} else {
						return "--";
					}
				}
			},{
				field : 'action',
				title : '操作',
				width : 200,
				formatter : function(value, row, index) {
					var str = '';
					<shiro:hasPermission name="user:lookUser">
					str += $.formatString('<img  onclick="lookUserInfo(\'{0}\');" src="{1}" title="查看"/>', row.id, 'plugins/xdtech/images/notes/note.png');
					</shiro:hasPermission>
					
					<shiro:hasPermission name="user:editUser">
					str += '&nbsp;';
					str += $.formatString('<img  onclick="editUserInfo(\'{0}\');" src="{1}" title="编辑"/>', row.id, 'plugins/xdtech/images/notes/note_edit.png');
					</shiro:hasPermission>
					
					<shiro:hasPermission name="user:setUserRoles">
					str += '&nbsp;';
					str += $.formatString('<img onclick="userRoleInfo(\'{0}\');" src="{1}" title="角色信息"/>', row.id, 'plugins/xdtech/images/notes/note_go.png');
					</shiro:hasPermission>
					<shiro:hasPermission name="user:disableUser">
					str += '&nbsp;';
					str += $.formatString('<img onclick="disableUser(\'{0}\');" src="{1}" title="禁用"/>', row.id, 'plugins/xdtech/images/notes/note_delete.png');
					</shiro:hasPermission>
					return str;
				}
			} ] ],
			onDbClickRow : null,
		});
	});
	
	/**
	 * 点击用户组树
	 */
	function clickUsergroup(groupId) {
		if(groupId) {
			usergroupId = groupId;
			searchUser(groupId);
		}
	}
	/**
	 * 新增用户
	 */
	function newUser(){
			parent.$.modalDialog({
				title : '新增用户',
				width : 500,
				height : 300,
				href : 'user.do?addUser',
				buttons : [ {
					text : '确定',
					handler : function() {
						parent.$.modalDialog.openner_userList = $('#user_userlist');
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				},{
					text : '取消',
					handler : function() {
						$.modalDialog.handler.dialog('destroy');
						$.modalDialog.handler = undefined;			}
				} ]
			});
	}
	/**
	 * 编辑用户
	 */
	function editUser(){
	    var row = $('#user_userlist').datagrid('getSelected');
	    if (row) {
	    	editUserInfo(row.id,'编辑用户');
		}else {
			alertMsg('请选择编辑用户.');
		} 
	}
	/**
	 * 查看用户
	 */
	function lookUserInfo(userId) {
		printLog('user.do?addUser&userId='+userId);
	    parent.$.modalDialog({
			title : '查看用户',
			width : 500,
			height : 300,
			href : 'user.do?addUser&userId='+userId
		});
	}
	/**
	 * 禁用用户
	 */
	function disableUser(userId) {
		
	}
	/**
	 * 用户角色分配
	 */
	function userRoleInfo(userId) {
		parent.$.modalDialog({
			title : '用户角色信息',
			width : 850,
			height : 540,
			href : 'user.do?userLinkRoles&userId='+userId,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_userList = $('#user_userlist');
					parent.$.modalDialog.handler.action();
				}
			},{
				text : '取消',
				handler : function() {
					$.modalDialog.handler.dialog('destroy');
					$.modalDialog.handler = undefined;			}
			} ]
		});
	}
	/**
	 * 编辑用户信息
	 */
	function editUserInfo(userId) {
		printLog('user.do?addUser&userId='+userId);
	    parent.$.modalDialog({
			title : '编辑用户',
			width : 500,
			height : 300,
			href : 'user.do?addUser&userId='+userId,
			buttons : [ {
				text : '确定',
				handler : function() {
					parent.$.modalDialog.openner_userList = $('#user_userlist');
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			},{
				text : '取消',
				handler : function() {
					$.modalDialog.handler.dialog('destroy');
					$.modalDialog.handler = undefined;			}
			} ]
		});
	}
	/**
	 * 删除用户
	 */
	function deleteUser(){
	    var row = $('#user_userlist').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('询问','你确认要删除用户吗?',function(r){
	            if (r){
	                $.post('user.do?deleteUser',{id:row.id},function(result){
	                	console.log(result);
	                    if (result.success){
	                        $('#user_userlist').datagrid('reload');    // reload the user data
	                    }
	                    showMsg(result.msg);
	                },'json');
	            }
	        });
	    }else {
	    	showMsg("请选择用户记录.");
	    }
	}
	//表格查询  
	function searchUser(){  
	    var params = $('#user_userlist').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
		if (isNotEmpty(usergroupId)) {
			params['usergroupId'] = usergroupId;
		}
	    
	    if(isNotEmpty(name)) {
	    	params['name'] = name;
	    }
		
		printLog(params);
	    userList.datagrid('reload'); //设置好查询参数 reload 一下就可以了  
	}  
	//刷新用户组
	function userRefreshGroup() {
		//重置用户，默认用户组选择学达平台
		usergroupId = null;
		$('#groupTree').tree('reload'); 
		userList.datagrid({
			queryParams: {
				time : new Date().getTime()
			}
		});
		searchUser();
	}
	
	function formatterSex(value,row,index){
		if (value=='M'){
			return "男";
		} else if (value=='F') {
			return "女";
		} else {
			return "--";
		}
	}
	
	function doSearchUser(value){
		name=value;
		searchUser();
    }
	
	function clearSearch() {
		name = '';
		$('#userSearchBox').searchbox('setValue','');
		userList.datagrid({
			queryParams: {
				time : new Date().getTime()
			}
		});
		searchUser();
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true,title:'用户组',collapsible:false,border:false,tools:[
					{
						iconCls:'icon-reload',
						handler:function(){userRefreshGroup()}
					}]" style="width:200px;">
	           <ul id="groupTree" class="easyui-tree" data-options="
			          url: '<%=basePath %>group.do?usergroupTree',
			          method: 'post',
			          animate: true,
			          lines:true,
			          formatter:function(node){
			                    var s = node.text;
			                    if (node.children.length>0){
			                        s += '&nbsp;<span style=\'color:blue;\'>' + node.children.length +'</span>';
			                    }
			                    return s;
			          },
			          onClick:function(node){
							clickUsergroup(node.id);
					  }
			      ">
			  </ul>
	</div>
	<div data-options="region:'center',border:false">
		<table id="user_userlist" data-options="fit:true,border:false"></table>
		
		<div id="userlist_search"> 
			<div style="margin-bottom:5px;padding-left:5px;margin-top: 5px;">
				<input id="userSearchBox" class="easyui-searchbox" data-options="prompt:'请输入用户名',searcher:doSearchUser"   />
				<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-clear',plain:true" onclick="clearSearch()">清空</a>
			</div>
			<div id=user_toolbar style="height: auto">
				<shiro:hasPermission name="user:addUser">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="newUser()">新增</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="user:deleteUser">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteUser()">删除</a>
				</shiro:hasPermission>
			</div>
		</div>
	</div>
</div>
