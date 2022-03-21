<%@ page import="com.ycjedu.javabean.student" %>
<%@ page import="com.ycjedu.javabean.score" %>
<%@ page import="com.ycjedu.server.ScoreServer" %>
<%@ page import="com.ycjedu.server.StudentServer" %>

<%--<%@ page import="dao.StudentD" %>--%>
<%--<%@ page import="vo.Student" %>--%>
<%--<%@ page import="dao.ScoreD" %>--%>
<%--<%@ page import="vo.Score" %>--%>
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
    student student = (student) session.getAttribute("info");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=student.getId()%>.jpeg"/>
            <h1><%=student.getName()%></h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="<%=request.getContextPath()%>/student/personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="main.jsp">成绩信息</a></li>
                <li><a onclick="return confirm('确认退出?');" href="<%=request.getContextPath()%>/exitservlet">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>成绩信息</h2>
            <hr/>
        </div>
        <div class="table">
            <table width="800" frame="box" align="center">
                <tr>
                    <th height="35">学号</th>
                    <th>姓名</th>
                    <th>专业</th>
                    <th>数据库</th>
                    <th>Android</th>
                    <th>JavaWeb</th>
                    <th>操作</th>
                </tr>
                <%
                    try {
                        score stu = ScoreServer.getScoreById(student.getId());
                        String name = student.getName();
                        String major =  student.getMajor();
                        String Dat=stu==null?"":stu.getDat();
                        String Android=stu==null?"":stu.getAndroid();
                        String Jsp=stu==null?"":stu.getJsp();
                %>
                <tr>
                    <td height="35"><%=student.getId()%></td>
                    <td><%=name%></td>
                    <td><%=major%></td>
                    <td><%=Dat%></td>
                    <td><%=Android%></td>
                    <td><%=Jsp%></td>
                    <td><a href="<%=request.getContextPath()%>/student/pdf.jsp?id=<%=stu.getId()%>&name=<%=name%>&major=<%=major%>&database=<%=stu.getDat()%>&android=<%=stu.getAndroid()%>&jsp=<%=stu.getJsp()%>">PDF</a></td>
                </tr>
                <%
                    }
                    catch (Exception e){
                        out.print(e);
                    }
                %>
            </table>
        </div>
    </div>
</div>
</body>
</html>

