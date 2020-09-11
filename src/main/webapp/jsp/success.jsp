<%@ page import="com.promvn.appDemo.po.Articles" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    Congratulations!
</head>
<br>
<body>
<text>${success}</text>
<br>

<c:forEach items="${list}" var="item">
    <br>
    <hr>
    <br>
    <c:out value="title: "></c:out>
    <c:out value="${item.getTitle()}"></c:out>
    <br>
    <c:out value="author: "></c:out>
    <c:out value="${item.getAuthor()}"></c:out>
    <br>
    <c:out value="date: "></c:out>
    <c:out value="${item.getDate()}"></c:out>
    <br>
    <c:out value="content: "></c:out>
    <c:out value="${item.getContent()}"></c:out>
    <br>
</c:forEach>
</body>
</html>