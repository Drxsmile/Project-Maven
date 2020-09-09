<%@ page import="com.promvn.appDemo.po.Articles" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset = UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!---->
<html>
<head>
    <title>save</title>
    <meta charset = UTF-8>
</head>

<body>
<p>save an article</p>

<form action="/save" method="post">
    <p>
        <input type="submit" name="submit" value="提交">
        <input type="reset" name="reset" value="reset">
    </p>

    <p>
        title: <input type="text" name="title" size="50" maxlength="50"><br>
        author: <input type="text" name="author" size="20" maxlength="20"><br>
    </p>

    <p>content</p>
    <textarea name="content" rows="30" cols="80"
              onpropertychange="if(this.scrollHeight>80)
          this.style.posHeight=this.scrollHeight+5">
    </textarea>
</form>

<br>
<text>${success}</text>
</body>
</html>