<%@page import="java.sql.Connection"%>
<%@page import="credentials.DBManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Heroku db test</title>
</head>
<body>
<%
	Connection con = DBManager.loadDriver();
	if(con!=null)
	{
		out.println("Database connected");
		
	}
%>
</body>
</html>