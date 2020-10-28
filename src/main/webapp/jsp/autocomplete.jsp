<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>autocomplete</title>
</head>

<body>
<p>search articles(autocomplete)</p>

<form name="form1" action="/search" method = get>
    <p>
        <input type="button" onclick="document.form1.submit()" value="search" id = "btn">
    </p>

    <p>please input keyword:<br>
        <input type="text" name="keyword"
               size="50" maxlength="50" id="pre"
               onchange="autocomplete()"><br>
    <ul id="list">
    <c:forEach items="${wordlist}" var="item">
        <c:out value="${item}"></c:out>
        <br>
    </c:forEach>
    </ul>

    </p>

</form>

<script>
    var input = document.getElementById('pre');
    var wordlist = document.getElementById('list');
    // input.oninput = autocomplete();

    function autocomplete(){
        var table = document.form1;
        if(input.value != ''){
            table.action = "/autocomplete";
            table.submit();
        }

    }


</script>
</body>
</html>