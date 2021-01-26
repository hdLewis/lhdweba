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
    <title>updatelhd</title>
    <%@ include file="inc.jsp" %>
    <script>
        function checklhd(lhda) {
            $.ajax({
                type: 'post',
                url: "lhd",
                data: {op: 'check', lhda: lhda},
                success: function (res) {
                    if (res.indexOf("exist") != -1) {
                        $("#yya").html("商品编号已被占用");
                        $("#tj").prop("disabled", true);
                    } else {
                        $("#yya").html("商品编号可用");
                        $("#tj").prop("disabled", false);
                    }
                }
            })
        }
    </script>
</head>
<body>
<%--<%--%>
<%--    java.util.Properties props=System.getProperties();--%>
<%--    out.print(props.keySet());--%>
<%--    out.print(weba.dao.LhdDao.getConn());--%>
<%--%>--%>
<div id="firstDiv">
    <div>
        <form id='kong' action="lhd" onsubmit="return verify(this)" method="post">
            <input type="hidden" name="op" value="insert">
            <font id="yya" color="red">
                ${requestScope.error}
            </font>
            <input type="text" id='lhda' name='lhda' value="${param.lhda}" placeholder="请输入数字串。。。"
                   onkeyup="checklhd(this.value)">
            <font id="yyb" color="red"></font>
            <input type="text" id='lhdb' name='lhdb' value="${param.lhdb}" placeholder="请输入浮点串。。。">
            <font id="yyc" color="red"></font>
            <input type="text" id='lhdc' name='lhdc' value="${param.lhdc}" placeholder="请输入字母和数字组且长度在8~12。。。">
            <%--        <font id="yyd" color="red"></font>--%>
            <%--        <input type="text" id='lhdd' name='lhdd' value="刘浩东" placeholder="请输入汉字。。。">--%>
            <%--        <font id="yye" color="red"></font><br>--%>
            <%--        <input type="date" id="lhde" name="lhde">--%>
            <input type="submit" value="submit" id="tj"/>
        </form>
    </div>
</div>
</body>
</html>
