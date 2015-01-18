<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
   String newLocn = "show.do?to&url=index";
   response.setHeader("Location",newLocn);
%>