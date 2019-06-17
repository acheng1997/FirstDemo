<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/17
  Time: 15:42
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
    <link rel="stylesheet" href="css/custom/login.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>登录</title>
</head>
<body>
<c:if test="${sessionScope.REGISTER_SUCCEED=='true'}">
    <script>
        alert("注册成功！")
    </script>
    <c:remove var="REGISTER_SUCCEED" scope="session"></c:remove>
</c:if>
<c:if test="${sessionScope.LOGIN_ERROR=='true'}">
    <script>
        alert("用户名与密码不符！")
    </script>
    <c:remove var="LOGIN_ERROR" scope="session"></c:remove>
</c:if>
<c:if test="${sessionScope.UPDATE_PWD_TRUE=='true'}">
    <script>
        alert("修改成功！")
    </script>
    <c:remove var="UPDATE_PWD_TRUE" scope="session"></c:remove>
</c:if>
<div class="container-fluid">
    <div class="row" id="row_01">
        <div id="div_GO">
            <input type="button" value="GO" id="a_go">
        </div>
    </div>
    <div id="form">
        <label>
            <div id="exit">
                <input type="button" value="×" id="exit_btn">
            </div>
        </label>
        <div id="form_input" style="position: relative">
            <form action="${pageContext.request.contextPath}/login" method="post" id="form_01">
                <p id="login_p">
                    登录
                </p>
                <input type="text" class="form-control my_input" name="userName" placeholder="用户名"><br>
                <input type="password" class="form-control my_input" name="password" placeholder="密码"><br>
                <label><input type="checkbox" value="true" id="checkbox_01" name="autoLogin">自动登录</label><br>
                <input type="submit" id="login_btn" class="btn btn-info" value="登录"><br>
            </form>
            <a href="register.jsp">注册账号</a>
            <a href="pwd.jsp" style="position: relative;float: right;right: 0px">忘记密码？</a>
        </div>
    </div>
</div>
<script src="js/jquery-1.10.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="js/jquery.validate.min.js"></script>
<script>
    var $a_go = $("#a_go");
    var $row = $("#row_01");
    var $form = $("#form");
    var $exit = $("#exit");
    var xs = setTimeout(function () {
        $row.css("opacity", "0.8");
        $form.slideDown("slow");
    },500);
    $a_go.click(function () {
        $row.css("opacity", "0.8");
        $form.slideDown("slow");
    });
    $exit.click(function () {
        $row.css("opacity", "1");
        $form.slideUp("slow");
    });
    $("#form_01").validate({
        rules: {
            userName: "required",
            password: "required"
        },
        messages: {
            userName: "用户名不能为空！",
            password: "密码不能为空！"
        }
    });
</script>
</body>
</html>
