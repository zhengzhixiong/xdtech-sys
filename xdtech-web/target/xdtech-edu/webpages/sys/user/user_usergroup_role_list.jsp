<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<easy:datagrid id="roles_userUsergroupRoleList" url="${url}" pagination="false"  singleSelect="false" checkbox="false">
	<easy:datagridColumn field="id"/>
   	<easy:datagridColumn field="name" fieldName="名称" width="100"/>
   	<easy:datagridColumn field="description" fieldName="描述" width="100"/>
</easy:datagrid>
