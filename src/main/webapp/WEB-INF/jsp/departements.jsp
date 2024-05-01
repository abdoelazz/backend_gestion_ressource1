<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Departments</title>
</head>
<body>
<h1>List of Departments</h1>
<ul>
    <%-- Iterate over the list of departments and display each department --%>
    <c:forEach var="departements" items="${departements}">
        <li>${departements.getNomDepartement}</li>
    </c:forEach>
</ul>
</body>
</html>
