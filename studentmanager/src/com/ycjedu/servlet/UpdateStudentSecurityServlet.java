package com.ycjedu.servlet;

import com.ycjedu.server.StudentServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateStudentSecurityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //更新学生的安全信息
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        int cnt = StudentServer.updateStudentPassWordAndEmail(id, password, email);
        if(cnt>0){
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert(\"修改成功\");window.location.href='login.jsp';</script>");
            writer.flush();
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
