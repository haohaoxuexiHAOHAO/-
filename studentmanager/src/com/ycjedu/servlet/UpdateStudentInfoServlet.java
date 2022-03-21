package com.ycjedu.servlet;

import com.ycjedu.javabean.student;
import com.ycjedu.server.StudentServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateStudentInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到修改后的信息
        String studentName = req.getParameter("StudentName");
        String studentSex = req.getParameter("StudentSex");
        String studentMajor = req.getParameter("StudentMajor");
        String studentNo = req.getParameter("StudentNo");
        List<String> NewInfo=new ArrayList<String>();
        NewInfo.add(studentNo);
        NewInfo.add(studentName);
        NewInfo.add(studentSex);
        NewInfo.add(studentMajor);
        //将修改的信息添加到list中然后去更新数据库
        StudentServer.updateStudentByList(NewInfo);

        resp.sendRedirect(req.getContextPath()+"/showpagestudentsservlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
