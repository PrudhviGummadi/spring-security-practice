<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>DBA Page</title>
</head>
<body>
Hello! <strong>${user}</strong>. Welcome to  DBA page.
<a href="<c:url value="/logout" />" >Logout</a>
</body>
</html>