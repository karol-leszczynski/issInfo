<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Główna</title>
    <link rel="stylesheet" href="/css/css.css">
</head>
<body>
<% response.addHeader("Refresh","5"); %>
<strong class="halfbread-up">
    Co się dzieje z międzynarodową stacją kosmiczną ISS?
</strong>
<div>
    <div class="container">

        <div class="indicator-fresh"
             style="left:${position.leftPicturePosition}px;top:${position.topPicturePosition}px"></div>
        <c:forEach items="${trail}" var="pastPosition">
            <div class="indicator-old"
                 style="left:${pastPosition.leftPicturePosition}px;top:${pastPosition.topPicturePosition}px"></div>
        </c:forEach>

    </div>
    <div class="halfbread-down">
        <c:choose>
            <c:when test="${empty currentSpeed}">
                <span>Właśnie okrąża ziemię z prędkością około: <strong>chwilkę...</strong></span><br/>
            </c:when>
            <c:otherwise>
                <span>Właśnie okrąża ziemię z prędkością około: <strong>${currentSpeed} km/h</strong></span><br/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty summedDistance}">
                <span>Od początku twojej wizyty przebyła już: <strong>chwilkę...</strong></span>
            </c:when>
            <c:otherwise>
                <span>Od początku twojej wizyty przebyła już: <strong>${summedDistance} km</strong></span>
            </c:otherwise>
        </c:choose>
    </div>
</div>


</body>
</html>
