<%@ page import="by.travel.touristagency.entity.User" %><%--
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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/logo/logo_title.webp" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css" type="text/css">
</head>
<body>
<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/logo/logo.webp"/>' alt="travel">
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
<div class="wrapper">
    <div class="form-box">
        <h2>Profile</h2>
        <form action="${pageContext.request.contextPath}/profile" method="post">
            <div class="input-box">
                <input type="text" name="username" id="username" value="${sessionScope.user.username}">
                <label>Username</label>
            </div>
            <div class="input-box">
                <input type="email" name="email" id="email" value="${sessionScope.user.email}">
                <label>Email</label>
            </div>
            <div class="input-box">
                <input type="password" name="password" id="password" value="${sessionScope.user.password}">
                <label>Password</label>
            </div>
            <div class="input-box">
                <input type="text" name="firstName" id="firstName" value="${sessionScope.profile.firstName}">
                <label>First name</label>
            </div>
            <div class="input-box">
                <input type="text" name="lastName" id="lastName" value="${sessionScope.profile.lastName}">
                <label>Last name</label>
            </div>
            <div class="input-box">
                <input type="text" name="birthday" id="birthday" value="${sessionScope.profile.birthday}">
                <label>Birthday</label>
            </div>
            <button type="submit" class="btn">Save changes</button>
        </form>
    </div>
</div>
<%--<form action="${pageContext.request.contextPath}/profile" method="post">--%>
<%--    <p><input type="text" name="password" id="password" value="${sessionScope.user.password}"></p><br>--%>
<%--    <p><input type="text" name="email" id="email" value="${sessionScope.user.email}"></p><br>--%>
<%--    <p><input type="text" name="username" id="username" value="${sessionScope.user.username}"></p><br>--%>
<%--    <p><input type="text" name="firstName" id="firstName" value="${sessionScope.profile.firstName}"></p><br>--%>
<%--    <p><input type="text" name="lastName" id="lastName" value="${sessionScope.profile.lastName}"></p><br>--%>
<%--    <p><input type="text" name="birthday" id="birthday" value="${sessionScope.profile.birthday}"></p><br>--%>
<%--    <button type="submit">Save</button>--%>
<%--</form>--%>

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
