<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 9/1/2023
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Vouchers</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/logo/logo_title.webp" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/booking.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/logo/logo.webp"/>' alt="travel">
        <a href="${pageContext.request.contextPath}/travel_by" class="logo">Travel.by</a>
    </div>
    <nav class="navbar">
        <a href="#">About</a>
        <c:if test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </c:if>
        <a href="${pageContext.request.contextPath}/post_voucher">Post a voucher</a>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/profile">Profile</a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>
        <a href="#">Contact</a>
    </nav>
</header>

<div class="container flex">
    <div class="left">
        <div class="main_image">
            <img src="${pageContext.request.contextPath}/image/voucher/belarus.webp" class="slide">
        </div>
        <div class="option flex">
            <img src="${pageContext.request.contextPath}/image/voucher/belarus.webp"
                 onclick="img('${pageContext.request.contextPath}/image/voucher/belarus.webp')">
            <img src="${pageContext.request.contextPath}/image/voucher/greece.webp"
                 onclick="img('${pageContext.request.contextPath}/image/voucher/greece.webp')">
            <img src="${pageContext.request.contextPath}/image/voucher/brazil.webp"
                 onclick="img('${pageContext.request.contextPath}/image/voucher/brazil.webp')">
            <img src="${pageContext.request.contextPath}/image/voucher/spain.webp"
                 onclick="img('${pageContext.request.contextPath}/image/voucher/spain.webp')">
            <img src="${pageContext.request.contextPath}/image/voucher/greece.webp"
                 onclick="img('${pageContext.request.contextPath}/image/voucher/greece.webp')">
            <img src="${pageContext.request.contextPath}/image/voucher/spain.webp"
                 onclick="img('${pageContext.request.contextPath}/image/voucher/spain.webp')">
        </div>
    </div>
    <div class="right">
        <h3>${param.name}</h3>
        <h4>${param.price} $</h4>
        <p>
            Spend an unforgettable time on the beautiful white sandy beaches of Paradiso Island.
            The island is known for its turquoise sea, crystal clear waters and picturesque scenery.
            Amazing landscapes, comfortable hotels and a wide range of entertainment await you.
        </p>
        <h5>Number of persons</h5>
        <form action="${pageContext.request.contextPath}/booking" method="post">
            <div class="wrapper">
                <span class="minus">-</span>
                <input type="text" value="1"/>
                <span class="plus">+</span>
            </div>
            <button type="submit">Book</button>
        </form>
    </div>
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
<script>
    <%@include file="/script/booking.js" %>
    <%@include file="/script/booking_button.js" %>
</script>
</body>
</html>

