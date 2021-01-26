<%--
  Created by IntelliJ IDEA.
  User: 刘浩东
  Date: 2020/8/18
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="inc.jsp" %>
<html>
<head>
    <title>showLhd</title>
</head>
<body>

<form action="lhd">
    <input name="lhdc" placeholder="请输入要查询的关键字（lhdc字段）">
    <input type="submit" value="查询">
</form>

<table class="table table-bordered">
    <tr><td>LHDA</td><td>LHDB</td><td>LHDC</td><td>OP</td></tr>

    <c:forEach items="${sessionScope.lstlhd}" var="y">
<tr>
   <td>${y.lhda}</td>  <td>${y.lhdb}</td> <td>${y.lhdc}</td>

    <td>
        <a href="lhd?op=delete&lhda=${y.lhda}" onclick="return confirm('del?')">del</a>
        &nbsp;
        <a href="updatelhd.jsp?lhda=${y.lhda}&lhdb=${y.lhdb}&lhdc=${y.lhdc}">update</a>
    </td>
</tr>

    </c:forEach>
    <a href="lhdb?op=logout" onclick="return confirm('确认要退出吗？')">logout</a>
</table>

</body>
</html>
