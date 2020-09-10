<%@ page import="com.promvn.appDemo.po.Articles" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset = UTF-8" language="java" %>

<html>
<head>
    <title>delete</title>
</head>

<body>
<p>delete an article</p>

<form name="form1" action="/delete" method = post>
    <p>
        <input type="submit" name="submit" value="delete">
    </p>

    <p>please input the title of the article:<br>
        <input type="text" name="title" size="50" maxlength="50"><br>
    </p>

</form>

</body>
</html>