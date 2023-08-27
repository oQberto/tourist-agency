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
<style><%@include file="/css/home_page.css"%></style>
</head>
<body>
<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/airplane-around-earth.webp"/>' alt="travel">
        <a href="#" class="logo">Travel.by</a>
    </div>
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}/registration">Registration</a>
        <a href="${pageContext.request.contextPath}/login">Login</a>
        <div>
            <c:if test="${not empty sessionScope.user}">
                <div id="logout">
                    <form action="${pageContext.request.contextPath}/logout" method="post">
                        <button type="submit">Logout</button>
                    </form>
                </div>
            </c:if>
        </div>
    </nav>
</header>

<div class="intro">
    <h1>Time to see the world.</h1>
    <h2>Vacation planning has never been so easy!</h2>
</div>

<div class="search_box">
    <input type="text" class="search_txt" placeholder="Search cruise, excursion, shopping tour...">
    <a href="#" class="search_btn">
        <img src='<c:url value="/image/search_button.svg"/>' alt="search">
    </a>
</div>

<div class="desc">
    <h1>Popular Companies</h1>
    <h2>Find your next tour for book anywhere in the World</h2>
</div>

<div class="companies">
    <a href="${pageContext.request.contextPath}/vouchers?companyId=1">
        <img src='<c:url value="/image/spain.webp"/>' alt="Spain Tour">
        <h3 class="company1">
            <span>Spain Tour</span>
        </h3>
    </a>
    <a href="${pageContext.request.contextPath}/vouchers?companyId=2">
        <img src='<c:url value="/image/greece.webp"/>' alt="Greece Tour">
        <h3 class="company2">
            <span>Greece Tour</span>
        </h3>
    </a>
    <a href="${pageContext.request.contextPath}/vouchers?companyId=3">
        <img src='<c:url value="/image/belarus.webp"/>' alt="Belarus Tour">
        <h3 class="company3">
            <span>Belarus Tour</span>
        </h3>
    </a>
    <a href="${pageContext.request.contextPath}/vouchers?companyId=4">
        <img src='<c:url value="/image/brazil.webp"/>' alt="Brazil Tour">
        <h3 class="company4">
            <span>Brazil Tour</span>
        </h3>
    </a>
</div>

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