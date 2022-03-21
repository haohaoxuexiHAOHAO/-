package com.ycjedu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //删除所有的cookie即可
        Cookie[] cookies = req.getCookies();
        if(cookies==null){
            //如果为空直接返回登陆界面
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }else {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if(name.equals("username")){
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
