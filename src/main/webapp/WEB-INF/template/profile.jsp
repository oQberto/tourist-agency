<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 8/29/2023
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/logo_title.webp" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" type="text/css">
</head>
<body>
<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/logo.webp"/>' alt="travel">
        <a href="${pageContext.request.contextPath}/travel_by" class="logo">Travel.by</a>
    </div>
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}#">About</a>
        <a href="${pageContext.request.contextPath}/post_voucher">Post a voucher</a>
        <a href="${pageContext.request.contextPath}#">Profile</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
        <a href="#">Contact</a>
    </nav>
</header>

<footer>
    <div class="footer-content">
        <h3>Travel.by</h3>
        <p>Thousands of tours and cruises for book across the World</p>
        <ul class="socials">
            <li><a href="#"><img src='<c:url value="/image/facebook.webp"/>' alt="facebook"></a></li>
            <li><a href="#"><img src='<c:url value="/image/github.webp"/>' alt="github"></a></li>
            <li><a href="#"><img src='<c:url value="/image/linkedin.webp"/>' alt="linkedin"></a></li>
        </ul>
    </div>
    <div class="footer-bottom">
        <p>copyright &copy;2023 travel.by designed by <span>Alex</span></p>
    </div>
</footer>
</body>
</html>
