<%--This application deploys to http://agileteam9.azurewebsites.net/ --%>

<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<b>
<% out.println("Hello World!"); %>
</b>

<h2>Hi There!</h2>
<br>
<h3>Date=<%= new Date() %></h3>

</body>
</html>