<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 8/26/2023
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Travel.by</title>
</head>
<body>
<div>
    <c:if test="${not empty sessionScope.user}">
        <div id="logout">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </c:if>
</div>
<div>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">Registration</button>
    </a>
    <a href="${pageContext.request.contextPath}/login">
        <button type="button">Login</button>
    </a>
</div>
<div>
    <div>
        <ul>
            <c:forEach var="company" items="${requestScope.companies}">
                <a href="${pageContext.request.contextPath}/vouchers?companyId=${company.id}">${company.name}</a><br>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>