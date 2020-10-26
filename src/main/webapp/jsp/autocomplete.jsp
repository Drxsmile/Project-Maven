<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
    <title>search</title>
</head>

<body>
<p>autocomplete</p>

<form name="form1" action="/search" method = get>
    <p>
        <input type="submit" name="submit" value="search">
    </p>

    <p>please input keyword:<br>
        <input type="text" name="keyword" size="50" maxlength="50"><br>
    <ul id="list">
    <c:forEach items="${wordlist}" var="item">
        <c:out value="${item}"></c:out>
    </c:forEach>
    </ul>

    </p>

</form>

</body>
</html>