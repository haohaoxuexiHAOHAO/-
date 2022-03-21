package com.ycjedu.Filter;

import com.ycjedu.server.StudentServer;
import com.ycjedu.server.TeacherServer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        //对登陆的用户名和密码进行验证
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        //将得到用户名和密码与数据匹配
        String IdCard="";
        boolean a = StudentServer.QueryStudent(username, password);
        boolean b = TeacherServer.QueryTeacher(username, password);
        if(a==true&&b==false)IdCard="学生";
        if(b==true&&a==false)IdCard="老师";
        //通过身份转发
        if(b==true){
            httpServletRequest.getRequestDispatcher("/teacherservlet").forward(servletRequest,servletResponse);
        }else if(a==true){
            httpServletRequest.getRequestDispatcher("/studentservlet").forward(servletRequest,servletResponse);
        }else{
            HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");
        }
    }
    @Override
    public void destroy() {
    }
}
