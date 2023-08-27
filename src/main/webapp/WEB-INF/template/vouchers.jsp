<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 8/27/2023
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Vouchers</title>
</head>
<body>
<div>
    <ul>
        <c:forEach var="voucher" items="${requestScope.vouchers}">
            <li>
                <a href="${pageContext.request.contextPath}/voucher">${voucher.name}</a><br>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
