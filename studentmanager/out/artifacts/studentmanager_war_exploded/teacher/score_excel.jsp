
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ycjedu.javabean.score" %>
<%@ page import="com.ycjedu.server.StudentServer" %>
<%@ page import="com.ycjedu.server.ScoreServer" %>
<%--
  Created by IntelliJ IDEA.
  User: 007
  Date: 2018/11/1
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="application/msexcel" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
</head>
<body>
<%
    out.clearBuffer();
    response.setHeader("Content-Disposition", "attachment;filename=excel.xls");
%>
<table align="center" border="1">
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
            ArrayList<score> stus = (ArrayList<score>) ScoreServer.getAllStudentsScore(1,1000);
            for (score stu : stus) {
                String name = StudentServer.getStudentById(stu.getId()).getName();
                String major = StudentServer.getStudentById(stu.getId()).getMajor();
    %>
    <tr>
            <td align="center"><%=stu.getId()%></td>
            <td align="center"><%=name%></td>
            <td align="center"><%=major%></td>
            <td align="center"><%=stu.getDat()%></td>
            <td align="center"><%=stu.getAndroid()%></td>
            <td align="center"><%=stu.getJsp()%></td>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
</body>
</html>

