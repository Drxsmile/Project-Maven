<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset = UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<script>
    function save(){
        document.save_form.submit();
    }
    function update(){
        document.update_form.submit();
    }
    function del(){
        document.delete_form.submit();
    }
    function search(){
        document.search_form.submit();
    }
</script>

<head>
    <title>DocRefiner</title>
    <meta charset="UTF-8">
</head>

<form name = "save_form" target="_blank" action="${pageContext.request.contextPath}/save" method = post></form>
<form name = "update_form" target="_blank" action="${pageContext.request.contextPath}/update" method = post></form>
<form name = "delete_form" target="_blank" action="${pageContext.request.contextPath}/delete" method = delete></form>
<form name = "search_form" target="_blank" action="${pageContext.request.contextPath}/search" method = get></form>

<body>
<p>Welcome to DocRefiner!</p>
<br>
<button onclick="save()">save</button>

<button onclick="update()">update</button>

<button onclick="del()">delete</button>

<button onclick="search()">search</button>
<hr>
<c:forEach items="${all}" var="item">
    <c:out value="title: "></c:out>
    <c:out value="${item.getTitle()}"></c:out>
    <br>
    <c:out value="author: "></c:out>
    <c:out value="${item.getAuthor()}"></c:out>
    <br>
    <c:out value="date: "></c:out>
    <c:out value="${item.getDate()}"></c:out>
    <br>
    <br>
</c:forEach>


</body>
</html>