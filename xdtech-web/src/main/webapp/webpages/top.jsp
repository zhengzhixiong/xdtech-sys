<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript" src="plugins/xdtech/js/menu2.js"></script>
<link rel="stylesheet" href="plugins/xdtech/css/head.css" />
<script type="text/javascript">
	function logout() {
		$.messager.confirm('系统提示', '您确认注销吗？', function(b) {
			if (b) {
				$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					url : 'authLogin.action?logout',// 请求的action路径
					error : function() {// 请求失败处理函数
						
					},
					success : function(data) {
						window.location.href='login.jsp';
					}
				});
// 				window.location.href="authLogin.action?logout";
			}
		});
	}
	//更改密码
	function updatePwd() {
		parent.$.modalDialog({
			title : '修改密码',
			width : 270,
			height : 200,
			href : 'user.do?toUpdatePwd',
			buttons : [ {
				text : '确定',
				handler : function() {
					var updatePwdForm = parent.$.modalDialog.handler.find('#updatePwdForm');
					updatePwdForm.submit();
				}
			},{
				text : '取消',
				handler : function() {
					$.modalDialog.handler.dialog('destroy');
					$.modalDialog.handler = undefined;
				}
			} ]
		});
	}
</script>

<div id="top">
	<div id="logo">
		<div class="logo_biaoti_box">
			<div class="logo_biaoti">
				<span>学达科技教务平台</span>
				<div class="logo_us r">
					<div class="logo_ustu"></div>
					<div class="logo_usfone">
						<strong>欢迎您！${user.name }</strong>
						<a href="javascript:void()" target="_blank" >帮助</a>
						<a href="javascript:void()">关于</a>
						<a href="javascript:void()" target="_self" onclick="updatePwd()">修改密码</a>
						<a href="javascript:void()" target="_self" onclick="logout()">注销</a>
					</div>
				</div>
			</div>
		</div>
		<div class="logo_an_box">
			<easy:menu menus="${menus}"></easy:menu>
		</div>
	</div>
</div>