<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学达科技</title>
<script type="text/javascript" src="<%=basePath%>plugins/xdtech/js/import.js"></script>
<link rel="shortcut icon" href="plugins/xdtech/images/favicon.png">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" 
			style="height: 81px; overflow: hidden;"
			href="<%=basePath %>main.do?top"></div>

		<!-- 中间-->
		<div data-options="region:'center',border:false">
			<div id="maintabs" class="easyui-tabs" data-options="fit:true">
				<div title="首页">
				
				</div>
			</div>
		</div>
		<div id="mm" class="easyui-menu" style="width: 150px;">
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseall">全部关闭</div>
			<div id="mm-tabcloseother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div id="mm-tabcloseright">当前页右侧全部关闭</div>
			<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		</div>
	</div>
</body>
</html>