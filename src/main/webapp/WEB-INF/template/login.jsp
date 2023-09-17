<%--
  Created by IntelliJ IDEA.
  User: ermak
  Date: 8/26/2023
  Time: 5:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Travel.by Login</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/logo/logo_title.webp" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
</head>
<body>
<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/logo/logo.webp"/>' alt="travel">
        <a href="${pageContext.request.contextPath}/travel_by" class="logo">Travel.by</a>
    </div>

    <nav class="navbar">
        <a href="#">About</a>
        <a href="#">Contact</a>
    </nav>
</header>
<div class="wrapper">
    <div class="form-box login">
        <c:if test="${not empty requestScope.registrationError}">
            <div class="error">
                <c:forEach var="error" items="${requestScope.registrationError}">
                <span>${error.message}<span>
                    </c:forEach>
            </div>
        </c:if>
        <h2>Login</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="input-box">
                <span class="icon"><img src='<c:url value="/image/icon/email.webp"/>' alt="mail"></span>
                <input type="email" name="email" id="email_log" value="${param.email}" required>
                <label>Email</label>
            </div>
            <div class="input-box">
                <span class="icon"><img src='<c:url value="/image/icon/padlock.webp"/>' alt="password"></span>
                <input type="password" name="password" id="password_log" required>
                <label>Password</label>
            </div>
            <div class="remember-forgot">
                <label><input type="checkbox">Remember me</label>
                <a href="#">Forgot Password?</a>
            </div>
            <button type="submit" class="btn">Login</button>
            <div class="login-register">
                <p>Don't have an account?
                    <a href="#" class="register-link">Register</a>
                </p>
            </div>
        </form>
    </div>

    <div class="form-box register">
        <h2>Registration</h2>
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <div class="input-box">
                <span class="icon"><img src='<c:url value="/image/icon/user.webp"/>' alt="mail"></span>
                <input type="text" name="userName" id="userName" required>
                <label>Username</label>
            </div>
            <div class="input-box">
                <span class="icon"><img src='<c:url value="/image/icon/email.webp"/>' alt="mail"></span>
                <input type="email" name="email" id="email_reg" required>
                <label>Email</label>
            </div>
            <div class="input-box">
                <span class="icon"><img src='<c:url value="/image/icon/padlock.webp"/>' alt="password"></span>
                <input type="password" name="password" id="password_reg" required>
                <label>Password</label>
            </div>
            <div class="remember-forgot">
                <label><input type="checkbox" required>I agree to the terms & conditions</label>
            </div>
            <button type="submit" class="btn">Register</button>
            <div class="login-register">
                <p>Already have an account? <a href="#" class="login-link">Login</a></p>
            </div>
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
    <%@include file="/script/script.js" %>
</script>
</body>
</html>
