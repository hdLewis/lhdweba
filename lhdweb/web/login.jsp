<%--
  Created by IntelliJ IDEA.
  User: 刘浩东
  Date: 2020/8/13
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Title</title>
    <%--    <link rel="stylesheet" href="static/css/lhd.css" type="text/css" />--%>
    <%@ include file="inc.jsp" %>
</head>
<body>

<div>

    <form id='lu' action="lhdb" onsubmit="return verify(this)" method="post">
        <input type="hidden" name="op" value="login">
        <font id="ts" color="red">
<%--            <% out.print(session.getAttribute("error"));%><br>--%>
<%--            <%=session.getAttribute("error")%><br>--%>
            ${sessionScope.error}
        </font>
        <input type="username" id='lhdaa' name='lhdaa' placeholder="请输入用户名。。。">
        <font id="kgzz" color="red"></font>
        <input type="password" id='lhdbb' name='lhdbb' placeholder="请输入密码。。。">
        <input type="submit" value="login"/>
        <input type="button" id='lhddd' name='lhddd' value="还没帐号" onclick="window.location.href='register.jsp'">
        <!-- 点击submit触发submit事件 -->
    </form>

</div>

</body>
</html>
