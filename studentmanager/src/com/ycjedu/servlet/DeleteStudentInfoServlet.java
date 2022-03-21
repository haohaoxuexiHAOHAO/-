package com.ycjedu.servlet;

import com.ycjedu.server.ScoreServer;
import com.ycjedu.server.StudentServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStudentInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //得到学生的id
        String key = req.getParameter("key");

        //删除该学生信息和成绩信息
        StudentServer.deleteStudentById(key);
        ScoreServer.deleteScoreById(key);

        resp.sendRedirect(req.getContextPath()+"/showpagestudentsservlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
