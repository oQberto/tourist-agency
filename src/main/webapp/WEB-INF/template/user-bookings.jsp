<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 9/3/2023
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Vouchers</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/logo/logo_title.webp" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vouchers.css" type="text/css">
</head>
<body>
<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/logo/logo.webp"/>' alt="travel">
        <a href="${pageContext.request.contextPath}/travel_by" class="logo">Travel.by</a>
    </div>
    <nav class="navbar">
        <a href="#">About</a>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/profile">Profile</a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>
        <a href="#">Contact</a>
    </nav>
</header>

<div class="vouchers">
    <c:forEach var="voucher" items="${sessionScope.user_vouchers}">
        <div class="voucher">
            <form action="${pageContext.request.contextPath}/user-bookings?voucherId=${voucher.id}&voucherName=${voucher.name}" method="post">
                <img src='<c:url value="/image/voucher/${voucher.image}"/>'>
                <h2>${voucher.name}</h2>
                <p>${voucher.description}</p>
                <span class="price">${voucher.price} $</span>
                <button type="submit">Remove</button>
            </form>
        </div>
    </c:forEach>
</div>

<footer>
    <div class="footer-content">
        <h3>Travel.by</h3>
        <p>Thousands of tours and cruises for book across the World</p>
        <ul class="socials">
            <li><a href="#"><img src='<c:url value="/image/icon/facebook.webp"/>' alt="facebook"></a></li>
            <li><a href="#"><img src='<c:url value="/image/icon/github.webp"/>' alt="github"></a></li>
            <li><a href="#"><img src='<c:url value="/image/icon/linkedin.webp"/>' alt="linkedin"></a></li>
        </ul>
    </div>
    <div class="footer-bottom">
        <p>copyright &copy;2023 travel.by designed by <span>Alex</span></p>
    </div>
</footer>
</body>
</html>
