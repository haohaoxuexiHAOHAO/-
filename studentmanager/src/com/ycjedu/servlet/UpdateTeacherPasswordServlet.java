package com.ycjedu.servlet;

import com.ycjedu.server.TeacherServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateTeacherPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //得到新密码和老师的id
        String password = req.getParameter("password");
        String id = req.getParameter("id");

        int cnt = TeacherServer.UpdateTeacherPassWordById(id, password);
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
