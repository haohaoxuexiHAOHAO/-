<%--<%@ page import="vo.Teacher" %>--%>
<%--<%@ page import="vo.Score" %>--%>
<%@ page import="java.util.ArrayList" %>
<%--<%@ page import="dao.StudentD" %>--%>
<%@ page import="com.ycjedu.javabean.teacher" %>
<%@ page import="com.ycjedu.javabean.score" %>
<%@ page import="com.ycjedu.server.StudentServer" %>
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
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    teacher teacher = (teacher) session.getAttribute("info");
    ArrayList<score> stus = (ArrayList<score>) session.getAttribute("allStudentsScore");
    int sumIndex = (int) session.getAttribute("sum_idx");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=teacher.getId()%>.jpeg"/>
            <h1><%=teacher.getId()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li><a href="/studentmanager/showpagestudentsservlet">学生管理</a></li>
                <li class="current_page_item"><a href="/studentmanager/showpagescoreservlet">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="<%=request.getContextPath()%>/exitservlet">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生成绩管理</h2>
            <hr/>
        </div>
        <form method="post" action="<%=request.getContextPath()%>/updatescoreservlet" style="height: 525px; margin-top: 20px">
            <input type="button" class="btn-add" onclick="location.href='score_excel.jsp';" value="导出EXCEL">
            <input type="submit" class="btn-add" style="float: right;margin-bottom: 30px" value="修改">
            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="800" frame="box" align="center">
                    <tr>
                        <th height="35">学号</th>
                        <th>姓名</th>
                        <th>专业</th>
                        <th>数据库</th>
                        <th>Android</th>
                        <th>JavaWeb</th>
                    </tr>
                    <%
                        try {
                            for (score stu : stus) {
                                String name = StudentServer.getStudentById(stu.getId()).getName();
                                String major = StudentServer.getStudentById(stu.getId()).getMajor();
                    %>
                    <tr>
                        <td height="35"><%=stu.getId()%></td>
                        <td><%=name%></td>
                        <td><%=major%></td>
                        <td><input value="<%=stu.getDat()%>" name="database" class="table-input"></td>
                        <td><input value="<%=stu.getAndroid()%>" name="android" class="table-input"></td>
                        <td><input value="<%=stu.getJsp()%>" name="jsp" class="table-input"></td>
                        <input value="<%=stu.getId()%>" name="id" type="hidden">
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </table>

            </div>
        </form>

        <%
            if (sumIndex > 1){
        %>
                <div id="index">
                    <a href="/studentmanager/showpagescoreservlet?index=1">首页</a>
                    <%
                        for (int i = 1; i <= sumIndex; i++) {
                    %>
                    <a href="/studentmanager/showpagescoreservlet?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="/studentmanager/showpagescoreservlet?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>

