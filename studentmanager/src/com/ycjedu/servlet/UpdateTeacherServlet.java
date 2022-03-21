package com.ycjedu.servlet;

import com.ycjedu.javabean.teacher;
import com.ycjedu.server.TeacherServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取更改后的老师的信息
        String teacherName = req.getParameter("TeacherName");
        String teacherSex = req.getParameter("TeacherSex");
        String teacherEmail = req.getParameter("TeacherEmail");
        String teacherPassword = req.getParameter("TeacherPassword");
        String teacherId = req.getParameter("TeacherId");

        List<String> Info=new ArrayList<>();
        Info.add(teacherId);
        Info.add(teacherName);
        Info.add(teacherSex);
        Info.add(teacherPassword);
        Info.add(teacherEmail);

        //将session中的老师信息修改
        HttpSession session = req.getSession();
        teacher info = (teacher)session.getAttribute("info");
        info.setName(teacherName);
        info.setPassword(teacherPassword);
        info.setEmail(teacherEmail);
        info.setSex(teacherSex);
        //将老师修改后的信息存入数据库
        TeacherServer.updateTeacherByList(Info);
        resp.sendRedirect(req.getContextPath()+"/teacher/personal.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
