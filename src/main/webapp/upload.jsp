
<%--
  Created by IntelliJ IDEA.
  User: 林小派
  Date: 2019/11/21
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传</title>
</head>
<body>
<form action="/api/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="filename" multiple="multiple"><br />

    <input type="submit" value="上传">


</form>
<p>${ msg}</p>
<p>${ url}</p>

<%--<p>${ msg }</p>--%>
<%--<p>${ url }</p>--%>
</body>
</html>
