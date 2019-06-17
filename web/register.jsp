<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/17
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/custom/register.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>注册</title>
</head>
<body>

<c:if test="${sessionScope.REGISTER_ERROR=='true'}">
    <script>
        alert("注册失败！")
    </script>
    <c:remove var="REGISTER_ERROR" scope="session"></c:remove>
</c:if>
<div class="container-fluid" id="div_big">
    <div id="div_go">
        <input type="button" value="GO" id="btn_go">
    </div>
    <div id="form">
        <label>
            <div id="exit">
                <input type="button" value="×" id="exit_btn">
            </div>
        </label>
        <div id="form_input">
            <form action="${pageContext.request.contextPath}/register" method="post" id="form_01">
                <label class="my_input_label"><p>用户名</p><input type="text" name="username" class="form-control"></label><br>
                <label class="my_input_label"><p>昵称</p><input type="text" name="nickname"
                                                              class="form-control"></label><br>
                <label class="my_input_label"><p>密码</p><input type="password" name="pwd"
                                                              class="form-control" id="mima"></label><br>
                <%--<label class="my_input_label"><p>确认密码</p><input type="password" name="password"--%>
                                                                <%--class="form-control"></label><br>--%>
                <label class="my_input_label"><p>手机号</p><input type="text" name="phone"
                                                               class="form-control"></label><br>
                <label class="my_input_label"><p>邮箱</p><input type="email" name="mail" class="form-control"></label><br>
                <input type="submit" id="register_btn" class="btn btn-info" value="注册">
            </form>
        </div>
    </div>
</div>
<script src="js/jquery-1.10.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="js/jquery.validate.min.js"></script>
<script>
    var $btn_go = $("#btn_go");
    var $div_big = $("#div_big");
    var $form = $("#form");
    var $exit = $("#exit");
    var xs = setTimeout(function () {
        $div_big.css("opacity", "0.8");
        $form.slideDown("slow");
    },500)
    $btn_go.click(function () {
        $div_big.css("opacity", "0.8");
        $form.slideDown("slow");
    });
    $exit.click(function () {
        $div_big.css("opacity", "1");
        $form.slideUp("slow");
    });
    $("#form_01").validate({
        rules: {
            username: {
                required: true
            },
            nickname: {
                required: true
            },
            pwd: {
                required: true,
                minlength:6,
                maxlength:16

            },
            password: {
                equalTo: "#mima"
            },
            phone: {
                required: true
            },
            mail: {
                required: true
            }
        },
        messages: {
            username: {
                required: "用户名不能为空！"
            },
            nickname: {
                required: "昵称不能为空！"
            },
            pwd: {
                required: "密码不能为空！",
                minlength:"密码长度太短！",
                maxlength:"密码长度过长！"
            },
            password: {
                equalTo: "两次密码不统一！"
            },
            phone: {
                required: "手机号不能为空！",
                phone: "手机号不合法！"
            },
            mail: {
                required: "邮箱不能为空！",
                mail: "邮箱格式不正确！"
            }
        }
    });
    $.validator.addMethod("phone", function (value, element, param) {
        if (!param) {
            return true;
        }
        var phoneRE = /^1[3,4,5,7,8,9][0-9]{9}$/;
        return phoneRE.test(value);
    });

    $.validator.addMethod("mail", function (value, element, param) {
        if (!param) {
            return true;
        }
        var phoneRE = /^.{1,}@.{1,}[.].{1,}$/;
        return phoneRE.test(value);
    });
</script>
</body>
</html>