<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Admin Page</title>
</head>
<body>
Hello! Welcome <strong>${user}</strong> to Admin Page
<a href="<c:url value="/logout" />" >Logout</a>
</body>
</html>