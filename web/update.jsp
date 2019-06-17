<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/20
  Time: 15:28
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
    <link rel="stylesheet" type="text/css" href="css/custom/update.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>资料添加与修改</title>
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
    </style>
</head>
<body style="background-image: url('img/else08.jpg'); background-repeat: no-repeat;
    background-size: 100% 100%;">
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="page-header">
            <h1 id="h1_01"> Integrated project
                <small>职工资料管理系统</small>
            </h1>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="position: relative">
            <ul class="nav navbar-nav">
                <li><a class="navbar-brand" href="home.jsp">主页</a></li>
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
                <li><a href="javascript:;" id="login_a">欢迎，${sessionScope.USER_INFO.nickName}</a></li>

            </ul>
        </div>
    </div>
</nav>
<div class="text-center"
     style="width: 100px;height: 50px;padding-top:5px;background-color: white; position:absolute;float: right;right: 20px;top: 172px;border-radius:10px;display:none;"
     id="logout_a">
    <a href="pwd.jsp"  id="password_a">修改密码 <span
            class="glyphicon glyphicon-edit"></span></a><br>
    <a href="${pageContext.request.contextPath}/logout" id="logout">注销 <span
            class="glyphicon glyphicon-share"></span></a>
</div>
<h1 class="text-center">资料添加修改</h1>
<div id="form">
    <div id="form_input" style="width: 400px;padding-bottom: 20px;margin: auto;">
        <form action="${pageContext.request.contextPath}/update" method="post" id="form_01">
            <p>姓名</p><input type="text" name="name" class="form-control"
                            value="${requestScope.UPDATE_STAFF.name}"></nobr>
            <p>性别</p><input type="text" name="sex" class="form-control" value="${requestScope.UPDATE_STAFF.sex}"></nobr>
            <p>基本工资</p><input type="text" name="basePay" class="form-control"
                              value="${requestScope.UPDATE_STAFF.basePay}"></nobr>
            <p>提成</p><input type="text" name="royalty" class="form-control"
                            value="${requestScope.UPDATE_STAFF.royalty}"></nobr>
            <p>部门</p><input type="text" name="department" class="form-control"
                            value="${requestScope.UPDATE_STAFF.department}"></nobr>
            <p>职位</p><input type="text" name="post" class="form-control"
                            value="${requestScope.UPDATE_STAFF.post}"></nobr>
            <p>手机</p><input type="text" name="phone" class="form-control"
                            value="${requestScope.UPDATE_STAFF.phone}">
            <input type="submit" id="register_btn" class="btn btn-info" value="提交">
        </form>
    </div>
</div>
<script src="js/jquery-1.10.1.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script>
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
    $("#form_01").validate({
        rules: {
            name: "required",
            sex: "required",
            basePay: "required",
            royalty: "required",
            department: "required",
            post: "required",
            phone: "required"
        },
        messages: {
            name: "姓名不能为空！",
            sex: "性别不能为空！",
            basePay: "工资不能为空！",
            royalty: "提成不能为空！",
            department: "部门不能为空！",
            post: "职位不能为空！",
            phone: "电话号码不能为空！"
        }
    });
</script>
</body>
</html>
