<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<script type="text/javascript">
	function loglist_refresh() {
		$("#loglist").datagrid('reload');
	}
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		<easy:datagrid id="loglist" title="日志列表" url="log.do?loadGridDatas"
			pagination="true" toolbar="loglist_toolbar">
			<easy:datagridColumn field="id" />
			<easy:datagridColumn field="createTime" fieldName="创建时间" width="80" />
			<easy:datagridColumn field="operator" fieldName="操作人" width="50" />
			<easy:datagridColumn field="ip" fieldName="IP地址" width="100" />
			<easy:datagridColumn field="operateAction" fieldName="操作动作" width="50" />
			<easy:datagridColumn field="operateComment" fieldName="操作说明" width="50" />
			<easy:datagridColumn field="operateTime" fieldName="操作时间" width="100" />
			<easy:datagridColumn field="method" fieldName="执行方法" width="200" />
		</easy:datagrid>
		<easy:toolbar id="loglist_toolbar">
			<easy:toolbarButton iconCls="icon-reload" labelName="刷新"
				operationCode="S010204" onclick="loglist_refresh()"></easy:toolbarButton>
		</easy:toolbar>
	</div>
</div>