
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Title</title>
    <%@ include file="inc.jsp" %>



    <script>
        function verify(frm) {
            var lhduser = frm.lhdaa1.value;
            // var lhdpass1=$("#lhdbb1").val();
            // var lhdpass2=$("#lhdbb2").val();

            var lhdpass1 = frm.lhdbb1.value;
            var lhdpass2 = frm.lhdbb2.value;
            if (lhduser == "" || lhdpass1 == "" || lhdpass2 == "" || lhdpass1 != lhdpass2) {
                $("#ts").html("用户或密码不能为空，且两次密码要一致");
                return false;
            }
            return true;
        }

        var xmlhttp = null;

        function checklhd(username) {
//原始使用方式
            // if (xmlhttp == null) {
            //     xmlhttp = new XMLHttpRequest();//第一步，创建异步通信对象
            //
            // }
            // //第二部，设定回调函数
            // xmlhttp.onreadystatechange = function () {
            //     if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //         $("#ts").html(xmlhttp.responseText);
            //     }
            // }
            // //第三步，发送异步请求
            // xmlhttp.open("get", "lhdb?op=check&username=" + username);
            // xmlhttp.send();
//========================================================
            $.ajax({
                type: 'post',
                url: "lhdb",
                data: {op: 'check', username: username},
                success: function (res) {
                    if (res.indexOf("exist") != -1) {
                        $("#ts").html("帐号已被占用");
                        $("#tj").prop("disabled", true);
                    } else {
                        $("#ts").html("帐号可用");
                        $("#tj").prop("disabled", false);
                    }
                }
            })
//=======================================================
            //
            // $.ajax({
            //     type:'post',
            //     url:"lhdb",
            //     data:{op:'check',username:username},
            //     success:function (res) {
            //         $('#ts').html(res)
            //     }
            // })

//异步登录校验
            // $(function () {
            //     $("tj").onclick(function () {
            //         if (verify()){
            //             $.ajax({
            //                 type: 'post',
            //                 url: 'lhdb',
            //                 data: {op:'register',lhduser:$("#lhduser").val(),lhdpass:$("#lhdpss").val()},
            //                 success:function(res){
            //                     if (res.indexOf("success")!=-1){
            //                         $("#ts").html("注册成功。。到哪个资源");
            //                         window.location.href="login.jsp";
            //                     }else {
            //                         $("#ts").html("注册失败");
            //                     }
            //                 }
            //             })
            //         }
            //     })
            // })
        }
    </script>
</head>
<body>
<img id="tp" src="static/image/d.jpg">
<script type="text/javascript">

    var tp=document.getElementById("tp");
    var tps=["static/image/d.jpg","static/image/h.jpg","static/image/w.jpg"];
    var i=0;
    function changePpic(){
        i++;
        if (i>=tps.length) i=0;
        tp.src=tps[i];
    }
    setInterval(changePpic,10000);
</script>
<div>


    
    
    <form id='lu' action="lhdb" onsubmit="return verify(this) " method="post">
        <input type="hidden" name="op" value="register">
        <font id="ts" color="red"></font>
        <input type="username" id='lhdaa1' name='lhdaa1' placeholder="请输入用户名。。。" onkeyup="checklhd(this.value)">
        <font id="kgzz" color="red"></font>
        <input type="password" id='lhdbb1' name='lhdbb1' placeholder="请输入密码。。。">
        <input type="password" id='lhdbb2' name='lhdbb2' placeholder="请输入确认密码。。。">
        <input type="submit" value="注册" disabled="disabled" id="tj"/>

        <!-- 点击submit触发submit事件 -->
    </form>

</div>

</body>
</html>
