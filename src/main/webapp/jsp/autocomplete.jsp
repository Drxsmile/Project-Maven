<%@ page contentType="text/html; charset = UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>autocomplete</title>
    <style>
        #myDiv {
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            margin-top: -50px;
            }
        .mouseOver{
            background-color: #708090;
            color:#FFFAFA;
            }

        .mouseOut{
            background-color: #FFFAFA;
            color:#000000;
            }
    </style>
</head>

<body>
<p>search articles(autocomplete)</p>

<div id="myDiv">
    <form name="form1" action="/search" method = get>
        <input type="text" size="50" name="keyword" id="keyword" onkeyup="getMoreContents()" onfocus="getMoreContents()" onblur="clearContent()"/>
        <input type="button" value="search" width="50px" onclick="form1.submit()"/>
    </form>
    <div id="popDiv">
        <table id="content_table" bgcolor="#FFFAFA" border="0" cellspacing="0" cellpadding="0">
            <tbody id="content_table_body">

            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">
    function getMoreContents(){
        var keyword = document.getElementById("keyword").value;
        if (keyword == ""){
            clearContent();
            return;
        }else{
            var ajax = new XMLHttpRequest();
            // console.log(keyword);
            var url = "autocomplete?keyword=" + keyword;
            ajax.open("GET", url, true);
            ajax.send(null);
            ajax.onreadystatechange = function(){
                if (ajax.readyState == 4) {
                    if (ajax.status == 200) {
                        var result = ajax.responseText;
                        insertContent(result);
                    }
                }
            }
        }
    }
    function insertContent(content){
        clearContent();
        setLocation();
        var json = JSON.parse(content);
        var len = json.length;
        for (var i = 0; i < len; i++){
            var value = json[i];
            var tr = document.createElement("tr");
            var td = document.createElement("td");
            td.setAttribute("bgcolor", "#FFFAFA");
            td.setAttribute("border", "0");
            td.onmouseover = function() {
                this.className = 'mouseOver';
            };
            td.onmouseout = function() {
                this.className = 'mouseOut';
            };
            td.onclick = function() {
                document.getElementById("keyword").value= td.innerText;
            };
            var text = document.createTextNode(value);
            td.appendChild(text);
            tr.appendChild(td);
            document.getElementById("content_table_body").appendChild(tr);
        }
        // clickTable();

    }
    function clickTable(){
        var table = document.getElementById("content_table");

        td.onclick = function() {
            document.getElementById("keyword").value= td.innerText;
        };
    }
    function clearContent() {
        var popNode = document.getElementById("popDiv");
        popNode.style.border = "none";
        var contentNode = document.getElementById("content_table_body");
        var len = contentNode.childNodes.length;
        for (var i = len - 1; i >= 0; i--) {
            contentNode.removeChild(contentNode.childNodes[i]);
        }
    }
    function setLocation(){
        var inputNode = document.getElementById("keyword");
        var width = inputNode.offsetWidth;
        var left = inputNode["offsetLeft"];
        var top = inputNode.offsetHeight+inputNode["offsetTop"];
        var popNode = document.getElementById("popDiv");
        popNode.style.border = "gray 0.5px solid";
        popNode.style.width = width+"px";
        popNode.style.top = top+"px";
        popNode.style.left = left+"px";
        document.getElementById("content_table").style.width=width+"px";
    }
</script>
</body>
</html>

