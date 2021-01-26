<%--
  Created by IntelliJ IDEA.
  User: hdLewis
  Date: 2020/8/24
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="lhd" class="lhdweb2.entity.Lhd"></jsp:useBean>

<jsp:setProperty property="*" name="lhd"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%
 out.print(lhd);
%>
<br>
<%=lhd%>
</body>
</html>
