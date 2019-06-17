<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/24
  Time: 9:18
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
    <link rel="stylesheet" href="css/custom/password.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>忘记密码</title>
    <style>
        #div_big {
            height: 610px;
            background-image: url("img/03.jpg");
            background-repeat: no-repeat;
            background-size: 100% 100%;
        }

        #form {
            padding-top: 40px;
            background-color: white;
            width: 400px;
            height: 400px;
            border-radius: 15px;
            position: absolute;
            top: 80px;
            left: 435px;
            display: none;
            box-shadow: 5px 5px 6px #adadad;
        }
        #div_GO {
            text-align: center;
        }
        #password_p {
            text-align: center;
            font-size: 50px;
            color: #2aabd2;
        }
        #a_go {
            border: none;
            font-size: 30px;
            background-color: transparent;
            color: white;
        }
        #exit {
            position: absolute;
            top: 10px;
            right: 8px;
        }
        #exit_btn {
            border: none;
            font-size: 20px;
            background-color: transparent;
        }
        #form_input {
            margin: 5px auto;
            width: 80%;
        }

        #login_btn {
            width: 100%;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.UPDATE_PWD_FALSE=='false'}">
    <script>
        alert("修改失败！")
    </script>
    <c:remove var="UPDATE_PWD_FALSE" scope="session"></c:remove>
</c:if>
<c:if test="${sessionScope.UPDATE_PWD_ERROR=='error'}">
    <script>
        alert("用户名与手机号不符！")
    </script>
    <c:remove var="UPDATE_PWD_ERROR" scope="session"></c:remove>
</c:if>
<c:if test="${sessionScope.UPDATE_PWD_NO=='no'}">
    <script>
        alert("没有此用户！")
    </script>
    <c:remove var="UPDATE_PWD_NO" scope="session"></c:remove>
</c:if>
<div class="container-fluid" id="div_big">
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
        <div id="form_input">
            <form action="${pageContext.request.contextPath}/forgetPassword" method="post" id="form_01">
                <p id="password_p">
                    修改密码
                </p>
                <input type="text" class="form-control my_input" name="userName" placeholder="用户名"><br>
                <input type="text" class="form-control my_input" name="phone" placeholder="手机号"><br>
                <input type="password" class="form-control my_input" name="password" placeholder="新密码"><br>
                <input type="submit" id="login_btn" class="btn btn-info" value="确认"><br>
            </form>
        </div>
    </div>
</div>
<script src="js/jquery-1.10.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="js/jquery.validate.min.js"></script>
<script>
    var $a_go = $("#a_go");
    var $div_big = $("#div_big");
    var $form = $("#form");
    var $exit = $("#exit");
    var xs = setTimeout(function () {
        $div_big.css("opacity", "0.8");
        $form.slideDown("slow");
    },500)
    $a_go.click(function () {
        $div_big.css("opacity", "0.8");
        $form.slideDown("slow");
    });
    $exit.click(function () {
        $div_big.css("opacity", "1");
        $form.slideUp("slow");
    });
</script>
</body>
</html>
