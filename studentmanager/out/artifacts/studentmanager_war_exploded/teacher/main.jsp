<%@ page import="java.util.ArrayList" %>
<%--<%@ page import="vo.Student" %>--%>
<%--<%@ page import="vo.Teacher" %>--%>
<%@ page import="com.ycjedu.javabean.teacher" %>
<%@ page import="com.ycjedu.javabean.student" %>
<%--
  Created by IntelliJ IDEA.
  User: 007
  Date: 2018/11/1
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    teacher teacher = (teacher) session.getAttribute("info");
    ArrayList<student> stus = (ArrayList<student>) session.getAttribute("allStudents");
    int sumIndex = (int) session.getAttribute("sum_idx");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="/studentmanager/userImg/<%=teacher.getId()%>.jpeg"/>
            <h1><%=teacher.getId()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="<%=request.getServletContext().getContextPath()%>/showpagestudentsservlet">学生管理</a></li>
                <li><a href="/studentmanager/showpagescoreservlet">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="<%=request.getContextPath()%>/exitservlet">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生信息管理</h2>
            <hr/>
            <button class="btn-add">添加学生</button>
            <div class="find">
                <form action="<%=request.getServletContext().getContextPath()%>/showpagestudentsservlet" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入学号或姓名搜索">
                    <input class="find-btn" type="submit" value="搜索">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    <th height="35">学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>入学日期</th>
                    <th>专业</th>
                    <th>操作</th>
                </tr>
                <%
                    for (student stu : stus) {
                %>
                        <tr>
                            <form method="post" action="<%=request.getContextPath()%>/updatestudentinfoservlet">
                                <td height="35"><%=stu.getId()%></td>
                                <td><input value="<%=stu.getName()%>" name="StudentName" class="table-input"></td>
                                <td><input value="<%=stu.getSex()%>" name="StudentSex" class="table-input"></td>
                                <td><%=stu.getSchool_date()%></td>
                                <td><input value="<%=stu.getMajor()%>" name="StudentMajor" class="table-input" style="width: 110px"></td>
                                <input value="<%=stu.getId()%>" name="StudentNo" type="hidden">
                                <td><input type="submit" class="update-btn" value="修改">&nbsp;<a class="btn-delete"
                                                                                           onclick="return confirm('确定要删除吗?');"
                                                                                           href="<%=request.getContextPath()%>/deletestudentinfoservlet?key=<%=stu.getId()%>">删除</a>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/showpagescoreservlet?key=<%=stu.getId()%>">查看成绩</a>
                                </td>
                            </form>
                        </tr>
                <%
                    }
                %>
            </table>
        </div>
        <%
            if (sumIndex > 1){
        %>
                <div id="index">
                    <a href="<%=request.getServletContext().getContextPath()%>/showpagestudentsservlet?index=1">首页</a>
                    <%
                        for (int i=1; i<=sumIndex; i++){
                    %>
                            <a href="<%=request.getServletContext().getContextPath()%>/showpagestudentsservlet?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="<%=request.getServletContext().getContextPath()%>/showpagestudentsservlet?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
</div>

<%--添加学生信息对话框--%>
<div id="add-dialog" title="添加学生信息">
    <form id="add-form" method="post">
        学号:<input name="StudentId" type="text"><br>
        姓名:<input name="StudentName" type="text"><br>
        性别:<input name="StudentSex" type="text"><br>
        专业:<input name="StudentMajor" type="text"><br>
        入学日期:<input name="EntrySchoolDate" type="month" style="width: 190px">
        <hr>
        <input style="float: right" type="submit" value="取消" onclick="function x() {
          $('#add-dialog').dialog('close');
        }">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='<%=request.getContextPath()%>/addstudentinfoservlet'">
    </form>
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>

<script>
    $('#add-dialog').dialog({
        width: 310,
        autoOpen: false,
        draggable: false,
        modal: true,
        resizable: false
    });
    $('.btn-add').click(function () {
        $('#add-dialog').dialog('open');
    });
</script>
</body>
</html>

