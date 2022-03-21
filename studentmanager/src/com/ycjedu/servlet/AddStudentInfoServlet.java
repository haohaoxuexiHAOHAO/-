package com.ycjedu.servlet;

import com.ycjedu.javabean.student;
import com.ycjedu.server.ScoreServer;
import com.ycjedu.server.StudentServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddStudentInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //获取学生的添加信息
        String studentId = req.getParameter("StudentId");
        String studentName = req.getParameter("StudentName");
        String studentSex = req.getParameter("StudentSex");
        String studentMajor = req.getParameter("StudentMajor");
        String studentDate = req.getParameter("EntrySchoolDate");

        List<String> Info=new ArrayList<String>();
        Info.add(studentId);
        Info.add(studentName);
        Info.add(studentSex);
        Info.add(studentMajor);
        Info.add(studentDate);


        //加入到数据库
        StudentServer.addStudentByList(Info);
        ScoreServer.addScoreById(studentId);

        resp.sendRedirect(req.getContextPath()+"/showpagestudentsservlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
