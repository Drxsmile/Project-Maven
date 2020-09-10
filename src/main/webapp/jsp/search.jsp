<%@ page import="com.promvn.appDemo.po.Articles" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>search</title>
</head>

<body>
<p>search articles</p>

<form name="form1" action="/search" method = get>
    <p>
        <input type="submit" name="submit" value="search">
    </p>

    <p>please input the title of the article:<br>
        <input type="text" name="keyword" size="50" maxlength="50"><br>
    </p>

</form>

<c:forEach items="${list}" var="item">
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