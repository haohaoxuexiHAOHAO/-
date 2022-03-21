<%--<%@ page import="dao.TeacherD" %>--%>
<%--<%@ page import="dao.StudentD" %>--%>
<%--<%@ page import="vo.Teacher" %>--%>
<%--<%@ page import="vo.Student" %>--%>
<%@ page import="com.ycjedu.javabean.teacher" %>
<%@ page import="com.ycjedu.javabean.student" %>
<%@ page import="com.ycjedu.server.TeacherServer" %>
<%@ page import="com.ycjedu.server.StudentServer" %>
<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: 007
  Date: 2018/10/25
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    teacher teacher = null;
    student student = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            String cookieName = c.getName();
            if ("username".equals(cookieName)) {
                String user = c.getValue();
                user=URLDecoder.decode(user,"UTF-8");
                try {
                    teacher = TeacherServer.QueryTeacher(user);
                    System.out.println(teacher);
                    student = StudentServer.QueryStudent(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (teacher != null) {
                    session.setAttribute("info", teacher);
                    response.sendRedirect("/studentmanager/showpagestudentsservlet");
                    return;
                }
                else if(student != null){
                    session.setAttribute("info", student);
                    response.sendRedirect("student/main.jsp");
                    return;
                }
            }
        }
    }
    response.sendRedirect("login.jsp");
%>
</body>
</html>
