<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/16
  Time: 8:52
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
    <link rel="stylesheet" type="text/css" href="css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/custom/home.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>主页</title>
    <style>
        #logout,#password_a{
            color: #222222;
        }
        #logout:hover {
             color: #adadad;
             text-decoration: none;
         }
        #password_a:hover {
            color: #adadad;
            text-decoration: none;
        }

        #update_nick, #exit_btn,#ok_btn {
            border: none;
            background-color: transparent;
        }
    </style>
</head>
<body style="background-image: url('img/else08.jpg'); background-repeat: no-repeat;
background-size: 100% 100%;">
<c:if test="${sessionScope.UPLOAD_ERROR=='true'}">
    <script>
        alert("请选择文件！")
    </script>
</c:if>
<c:remove var="UPLOAD_ERROR" scope="session"></c:remove>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="page-header">
            <h1 id="h1_01"> Integrated project
                <small>职工资料管理系统</small>
            </h1>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="position: relative">
            <ul class="nav navbar-nav">
                <li class="active"><a class="navbar-brand" href="home.jsp">主页</a></li>
                <li><a href="${pageContext.request.contextPath}/data?number=4&pageIndex=1">资料查看</a></li>
                <li><a href="upload.jsp">资料分享</a></li>
                <li><a href="${pageContext.request.contextPath}/download?number=4&pageIndex=1">资料下载</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.USER_INFO.profilePhoto==null}">
                    <li>
                        <div style="width:35px;height: 35px;position: absolute;float: right;right: 0px;top: 7px;border-radius: 50%">
                            <a href=""><img src="http://localhost:8080/img/aaac054c-9d2c-4e2d-8411-646c9b10fed6.jpg"
                                            alt="头像" class="img-circle" style="width: 35px"></a></div>
                    </li>
                </c:if>
                <c:if test="${sessionScope.USER_INFO.profilePhoto!=null}">
                    <li>
                        <div style="width:35px;height: 35px;position: absolute;float: right;right: 0px;top: 7px;border-radius: 50%;overflow: hidden;">
                            <a
                                    href=""><img src="${sessionScope.USER_INFO.profilePhoto}" alt="头像"
                                                 style="height: 35px"></a></div>
                    </li>
                </c:if>
                <li><a href="javascript:;" id="login_a" class="info">欢迎，${sessionScope.USER_INFO.nickName}</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="text-center info"
     style="width: 100px;height: 50px;padding-top:5px;background-color: white; position:absolute;float: right;right: 20px;top: 172px;border-radius:10px;display:none;"
     id="logout_a">
    <a href="pwd.jsp"  id="password_a">修改密码 <span
            class="glyphicon glyphicon-edit"></span></a><br>
    <a href="${pageContext.request.contextPath}/logout"  id="logout">注销 <span
            class="glyphicon glyphicon-share"></span></a>
</div>
<div class="text-center"><h1>个人资料</h1></div>

<div id="div_data" style="width: 700px;height: 330px;margin: auto; background-color: white;
border-radius: 20px;padding-top: 25px;padding-bottom: 25px;position: relative">
    <c:if test="${sessionScope.USER_INFO.profilePhoto==null}">
    <span style="margin-left: 30px"><img src="http://localhost:8080/img/aaac054c-9d2c-4e2d-8411-646c9b10fed6.jpg"
                                         alt="头像" class="" style="width: 250px;"></span></c:if>
    <c:if test="${sessionScope.USER_INFO.profilePhoto!=null}">
    <span style="margin-left: 30px"><img src="${sessionScope.USER_INFO.profilePhoto}"
                                         alt="头像" class="" style="width: 250px;"></span></c:if>
    <form action="${pageContext.request.contextPath}/profilePhoto?id=${sessionScope.USER_INFO.id}"
          enctype="multipart/form-data" method="post">
        <input type="file" name="files" id="files" style="display: none;"/>
        <div>
            <button type="button" id="selectFile" class="btn btn-info btn-lg" style="margin-left: 30px">
                <span class="glyphicon glyphicon-picture"></span>
                修改头像
            </button>
            <input type="submit" class="btn btn-info" value="上传"
                   style="margin-top: 5px;">
        </div>
    </form>
    <div style="margin: auto;position: absolute;float: right;right: 50px;top: 25px;">
        <p style="font-weight: 600;font-size: 15px;line-height: 2">用户名：${sessionScope.USER_INFO.name}</p>
        <p style="font-weight: 600;font-size: 15px;line-height: 2">昵称：${sessionScope.USER_INFO.nickName}</p>
        <button id="update_nick" style="position: absolute;float: right;top: 45px;right: 133px"><span
                class="glyphicon glyphicon-edit"></span></button>
        <div id="div_update" style="position: absolute;top: 42px;right: -10px;display: none">
            <form action="${pageContext.request.contextPath}/updateNickNameServlet?id=${sessionScope.USER_INFO.id}"
                  method="post" class="form-inline">
                <input type="" class="form-control my_input" name="nickName" style="width: 80px;height: 25px">
                <%--<input type="submit" class="btn btn-info" value="确定" style="height: 25px;padding-top: 3px">--%>
                <button type="submit" id="ok_btn"><span class="glyphicon glyphicon-check"></span></button>
                <input type="button" value="×" id="exit_btn">
            </form>
        </div>
        <p style="font-weight: 600;font-size: 15px;line-height: 2">手机号：${sessionScope.USER_INFO.phone}</p>
        <p style="font-weight: 600;font-size: 15px;line-height: 2">邮箱：${sessionScope.USER_INFO.email}</p>
        <p style="font-weight: 600;font-size: 15px;line-height: 2">上次登录时间：${sessionScope.USER_INFO.lastTime}</p>
        <p style="font-weight: 600;font-size: 15px;line-height: 2">本次登录时间：${sessionScope.USER_INFO.nowTime}</p>
    </div>
</div>
<script src="js/jquery-1.10.1.js"></script>
<script>
    var $update_nick = $("#update_nick");
    var $div_update = $("#div_update");
    var $exit_btn = $("#exit_btn");
    $update_nick.click(function () {
        $div_update.fadeIn("slow");
    });
    $exit_btn.click(function () {
        $div_update.fadeOut("slow");
    });
    $(document).ready(function () {
        $("#login_a").hover(function () {
            $("#logout_a").slideDown()
        }, function () {

        })
        $("#logout_a").hover(function () {

        }, function () {
            $("#logout_a").slideUp()
        })
    });

    $(function () {
        $('#selectFile').click(function () {
            $('#files').click();
        })
        $('#files').change(function () {
            var file = $(this).val();
            $('#fname-box').text(file);
        });
    })
</script>
</body>
</html>
