<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/18
  Time: 13:51
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
    <link rel="stylesheet" type="text/css" href="css/custom/download.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>资料下载</title>
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
<c:if test="${sessionScope.UPLOAD_TRUE == 'true'}">
    <script>
        alert("上传成功!")
    </script>
    <c:remove var="UPLOAD_TRUE" scope="session"></c:remove>
</c:if>
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
                <li class="active"><a href="javascript:;">资料下载</a></li>
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
<h1 class="text-center">资料下载</h1>
<div style="margin: 10px auto;padding: 25px;width: 1200px;height: 320px; background-color:white;border-radius: 20px;position: relative">
    <table class="table table-hover text-center">
        <tr>
            <th class="text-center">网络访问路径</th>
            <th class="text-center">文件名</th>
            <th class="text-center">上传者</th>
            <th class="text-center">文件大小</th>
            <th class="text-center">下载次数</th>
            <th class="text-center">下载</th>
        </tr>
        <c:forEach items="${requestScope.DOWNLOAD_OBJ.myFiles}" var="myFile">
            <tr>
                <td><a href="${myFile.filePath}" target="_blank">${myFile.filePath}</a></td>
                <td>${myFile.fileName}</td>
                <td>${myFile.userName}</td>
                <td>${myFile.fileSize}</td>
                <td>${myFile.fileNumber}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/fileDownload?fileName=${myFile.fileDownload}&id=${myFile.id}"
                    ><span class="glyphicon glyphicon-download-alt"></span></a></td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation">
        <ul class="pagination" style="position: absolute;right: 30px;bottom: 0px">
            <c:if test="${requestScope.DOWNLOAD_OBJ.pageIndex ==1}">
                <li class="disabled">
                    <a href="javascript:;" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${requestScope.DOWNLOAD_OBJ.pageIndex!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/download?number=4&pageIndex=${requestScope.DOWNLOAD_OBJ.pageIndex-1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${requestScope.DOWNLOAD_OBJ.pageNumber}" varStatus="vs">
                <c:if test="${vs.index == requestScope.DOWNLOAD_OBJ.pageIndex}">
                    <li class="active"><a href="javascript:;">${vs.index}</a></li>
                </c:if>
                <c:if test="${vs.index != requestScope.DOWNLOAD_OBJ.pageIndex}">
                    <li>
                        <a href="${pageContext.request.contextPath}/download?number=4&pageIndex=${vs.index}">${vs.index}</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${requestScope.DOWNLOAD_OBJ.pageIndex==requestScope.DOWNLOAD_OBJ.pageNumber}">
                <li class="disabled">
                    <a href="javascript:;" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${requestScope.DOWNLOAD_OBJ.pageIndex!=requestScope.DOWNLOAD_OBJ.pageNumber}">
                <li>
                    <a href="${pageContext.request.contextPath}/download?number=4&pageIndex=${requestScope.DOWNLOAD_OBJ.pageIndex+1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
<script src="js/jquery-1.10.1.js"></script>
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
</script>
</body>
</html>