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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/logo/logo_title.webp" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home_page.css" type="text/css">
</head>
<body>

<header class="header">
    <div class="logo_div">
        <img src='<c:url value="/image/logo/logo.webp"/>' alt="travel">
        <a href="#" class="logo">Travel.by</a>
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

<div class="intro">
    <h1>Time to see the world.</h1>
    <h2>Vacation planning has never been so easy!</h2>
</div>

<div class="search_box">
    <input type="text" class="search_txt" placeholder="Search cruise, excursion, shopping tour...">
    <a href="#" class="search_btn">
        <img src='<c:url value="/image/icon/search_button.svg"/>' alt="search">
    </a>
</div>

<div class="desc">
    <h1>Popular Companies</h1>
    <h2>Find your next tour for book anywhere in the World</h2>
</div>

<div class="companies">
    <c:forEach var="company" items="${requestScope.companies}">
        <a href="${pageContext.request.contextPath}/vouchers?companyId=${company.id}">
            <img src='<c:url value="/image/company_preview/${company.image}"/>' alt="${company.name}">
            <h3 class="company${company.id}">
                <span>${company.name}</span>
            </h3>
        </a>
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