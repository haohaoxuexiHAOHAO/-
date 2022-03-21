package com.ycjedu.servlet;

import com.ycjedu.javabean.student;
import com.ycjedu.javabean.teacher;
import com.ycjedu.server.StudentServer;
import com.ycjedu.server.TeacherServer;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存学生的名字到cookie中
        String username = URLEncoder.encode(req.getParameter("username"), "UTF-8");
        String pwd=req.getParameter("password");
        Cookie cookie = new Cookie("username",username);
        //cookie中的数据保存30天
        cookie.setMaxAge(3600*12*30);
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
        //通过学生的姓名和密码获取学生的信息
        //上面将username编码l现在先解码
        String username_src= URLDecoder.decode(username,"UTF-8");
        student student = StudentServer.getStudentByNameAndPassWord(username_src,pwd);
        //将学生信息保存到session
        HttpSession session = req.getSession();
        session.setAttribute("info",student);
        //请求转发到ShowPageStudents
        req.getRequestDispatcher("/student/main.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
