package com.ycjedu.servlet;


import com.ycjedu.javabean.teacher;
import com.ycjedu.server.TeacherServer;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class TeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //保存教师的名字到cookie中
        String username = URLEncoder.encode(req.getParameter("username"), "UTF-8");
        String pwd=req.getParameter("password");
        Cookie cookie = new Cookie("username",username);
        //cookie中的数据保存30天
        cookie.setMaxAge(3600*12*30);
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
        //通过老师的姓名和密码获取老师的信息
        //上面将username编码l现在先解码
        String username_src= URLDecoder.decode(username,"UTF-8");
        teacher teacher = TeacherServer.getTeacherByNameAndPwd(username_src, pwd);
        //将老师信息保存到session
        HttpSession session = req.getSession();
        session.setAttribute("info",teacher);
        //请求转发到ShowPageStudents
        req.getRequestDispatcher("/showpagestudentsservlet").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
