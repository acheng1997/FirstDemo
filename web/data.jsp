<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/20
  Time: 9:50
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
    <link rel="stylesheet" type="text/css" href="css/custom/data.css">
    <link rel="shortcut icon" href="img/favicon.png">
    <title>资料</title>
    <style>
        #logout, #password_a {
            color: #222222;
        }
        #update_a{
            color: #2aabd2;
        }
        #delete_a {
            color: tomato;
        }

        #logout:hover,#password_a:hover,#delete_a:hover,#update_a:hover {
            color: #adadad;
            text-decoration: none;
        }

    </style>
</head>
<body style="background-image: url('img/else08.jpg'); background-repeat: no-repeat;
    background-size: 100% 100%;">
<c:if test="${sessionScope.UPDATE_TRUE == 'true'}">
    <script>
        alert("修改成功!")
    </script>
</c:if>
<c:remove var="UPDATE_TRUE" scope="session"></c:remove>
<c:if test="${sessionScope.UPDATE_FALSE == 'false'}">
    <script>
        alert("修改失败!")
    </script>
</c:if>
<c:remove var="UPDATE_FALSE" scope="session"></c:remove>
<c:if test="${sessionScope.INSERT_TRUE == 'true'}">
    <script>
        alert("添加成功!")
    </script>
</c:if>
<c:remove var="INSERT_TRUE" scope="session"></c:remove>
<c:if test="${sessionScope.INSERT_FALSER == 'false'}">
    <script>
        alert("添加失败!")
    </script>
</c:if>
<c:remove var="INSERT_FALSER" scope="session"></c:remove>
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
                <li class="active"><a href="javascript:;">资料查看</a></li>
                <li><a href="upload.jsp">资料分享</a></li>
                <li><a href="${pageContext.request.contextPath}/download?number=4&pageIndex=1">资料下载</a></li>
            </ul>
            <form class="navbar-form navbar-left" id="findinput">
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
     style="width: 100px;height:50px;padding-top:5px;background-color: white; position:absolute;float: right;right: 20px;top: 172px;border-radius:10px;display:none;"
     id="logout_a">
    <a href="pwd.jsp" id="password_a">修改密码 <span
            class="glyphicon glyphicon-edit"></span></a><br>
    <a href="${pageContext.request.contextPath}/logout" id="logout">注销 <span
            class="glyphicon glyphicon-share"></span></a>
</div>
<h1 class="text-center" id="h1_02">职工资料</h1>
<div id="my_table"
     style="margin: 10px auto;padding: 25px;width: 1200px;height: 320px; background-color:white;border-radius: 20px;position: relative">
    <table class="table table-hover text-center">
        <tr>
            <th>姓名</th>
            <th>性别</th>
            <th>基本工资</th>
            <th>提成</th>
            <th>部门</th>
            <th>职位</th>
            <th>手机号</th>
            <th>功能</th>
        </tr>
        <c:forEach items="${requestScope.DATA_OBJ.staffs}" var="staff">
            <tr>
                <td>${staff.name}</td>
                <td>${staff.sex}</td>
                <td>${staff.basePay}</td>
                <td>${staff.royalty}</td>
                <td>${staff.department}</td>
                <td>${staff.post}</td>
                <td>${staff.phone}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/toUpdate?id=${staff.id}&pageIndex=${requestScope.DATA_OBJ.pageIndex}"
                    ><span class="glyphicon glyphicon-edit" id="update_a"></span></a>

                    <a href="${pageContext.request.contextPath}/delete?id=${staff.id}&pageIndex=${requestScope.DATA_OBJ.pageIndex}&number=4"
                       name="remove" id="delete_a"><span
                            class="glyphicon glyphicon-trash delete_a"></span></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<nav aria-label="Page navigation">
    <ul class="pagination" style="position: absolute;left: 1050px;bottom: 60px">
        <c:if test="${requestScope.DATA_OBJ.pageIndex==1}">
            <li class="disabled">
                <a href="javascript:;" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.DATA_OBJ.pageIndex!=1}">
            <li>
                <a href="${pageContext.request.contextPath}/data?number=4&pageIndex=${requestScope.DATA_OBJ.pageIndex-1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${requestScope.DATA_OBJ.pageNumber}" varStatus="vs">
            <c:if test="${vs.index == requestScope.DATA_OBJ.pageIndex}">
                <li class="active"><a href="javascript:;">${vs.index}</a></li>
            </c:if>
            <c:if test="${vs.index != requestScope.DATA_OBJ.pageIndex}">
                <li><a href="${pageContext.request.contextPath}/data?number=4&pageIndex=${vs.index}">${vs.index}</a>
                </li>
            </c:if>
        </c:forEach>
        <c:if test="${requestScope.DATA_OBJ.pageIndex==requestScope.DATA_OBJ.pageNumber}">
            <li class="disabled">
                <a href="javascript:;" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${requestScope.DATA_OBJ.pageIndex!=requestScope.DATA_OBJ.pageNumber}">
            <li>
                <a href="${pageContext.request.contextPath}/data?number=4&pageIndex=${requestScope.DATA_OBJ.pageIndex+1}"
                   aria-label="Previous">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>
<div class="text-center" style="position: absolute;left: 350px;bottom: 80px">
    <a href="${pageContext.request.contextPath}/toUpdate?number=4&pageIndex2=${requestScope.DATA_OBJ.pageIndex}"
       style="width: 580px" class="btn btn-info">添加</a>
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

    $(".delete_a").click(function () {
        var a = confirm("确定删除吗?");
        if (a) {
            return true;
        }
        return false;
    })

    // $('#findinput').keydown(function(event){
    //     if(event.keyCode == 13){
    //         var keyword = $(this).val()
    //         // alert(keyword)
    //         $('tr').hide()//将原有的内容隐藏
    //         //然后将含有keyword的li进行渐变显示
    //         $("td").filter(":Contains("+keyword+")").parents('.todo-ltem').fadeIn(2000)
    //     }
    // })
</script>
</body>
</html>