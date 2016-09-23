<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="student">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/bootstrap.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angular.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/studentCtrl.js"></script>
</head>

<body ng-controller="studentCtrl">
<table class="table">
    <tr>
        <th>Id</th>
        <th>Name</th>
    </tr>
    <c:forEach items="${requestScope.students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.firstName}</td>
            <td>${student.secondName}</td>
        </tr>
    </c:forEach>
</table>
<form class="form-inline">
    <label>Student name:</label>
    <input type="text" ng-model="studentName">
    <button class="btn-default" ng-click="addStudent()">Add student</button>
</form>
</body>
</html>
