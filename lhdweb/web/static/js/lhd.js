//表单 校验
function verify(frm) {
    var a = frm.getElementsByTagName("input");
    //是数字
    var b = a[0].value;
    flagb = true;
    if (b == null || b.length == 0) {
        //s document.getElementById("yy").innerHTML="第一行，必须是纯数字";
        $("#yya").html("必须是纯数字");
        flagb = false;
    }
    for (var i = 0; i < b.length; i++) {
        var ch = b.charAt(i);
        if (!(ch >= '0' && ch <= '9')) {
            document.getElementById("yya").innerHTML = "必须是纯数字";
            flagb = false;
        }
    }
    //统计数字和点的个数，是浮点数
    var c = a[1].value;
    flagc = true;
    if (c == null || c == "" || c == "." || c.indexOf(".") != c.lastIndexOf(".")) {
        document.getElementById("yyb").innerHTML = "必须是浮点数";
        flagc = false;
    }
    for (var i = 0; i < c.length; i++) {
        var ch = c.charAt(i);
        if (!((ch >= '0' && ch <= '9') || (ch == '.'))) {
            document.getElementById("yyb").innerHTML = "必须是浮点数";
            flagc = false;
        }
    }
    //校验是否是，数字加字母，长度在8~12
    var d = a[2].value;
    flagd = true;
    var dd = d.length;
    var ddd1 = /\d/;
    var ddd2 = /[a-zA-Z]/;
    var ddd3 = /[^\da-zA-Z]/;
    if (!ddd1.test(d) || !ddd2.test(d) || ddd3.test(d) || dd < 8 || dd > 12) {
        // alert("第三行，必须是数字和字母组成且长度在8~12");
        document.getElementById("yyc").innerHTML = "必须是数字和字母组成且长度在8~12";
        flagd = false;
    }
    // 必须是汉字
    var e = a[3].value;
    flage = true;
    var ee = /^[\u4E00-\u9FA5]+$/;
    if (!ee.test(e)) {
        //alert("第四行，必须是汉字");
        document.getElementById("yyd").innerHTML = "必须是汉字";
        flage = false;
    }
    // 日期不为空
    var f = a[4].value;
    flagf = true;
    if (f == null || f == "") {
        document.getElementById("yye").innerHTML = "日期不能为空";
        flagf = false;
    }
    //校验返回值
    if (flagb && flagc && flagd && flage && flagf) {
        return true;
    } else {
        return false;
    }
}