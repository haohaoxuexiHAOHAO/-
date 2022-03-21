package com.ycjedu.Filter;


import com.ycjedu.javabean.student;
import com.ycjedu.javabean.teacher;
import com.ycjedu.server.StudentServer;
import com.ycjedu.server.TeacherServer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RegisterCheckFilter implements Filter {
    //当前已经用到的id
    private BigInteger teacher_id;
    private BigInteger student_id;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取当前教师的最大id
        String maxId = TeacherServer.getMaxId();
        teacher_id=new BigInteger(maxId);
        //获取当前学生的最大id
        String maxId2 =  StudentServer.getMaxId();
        student_id=new BigInteger(maxId2);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        //拿去到注册界面提交的信息
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        String code = httpServletRequest.getParameter("code");
        String idCard = httpServletRequest.getParameter("IdCard");

        //先判断验证码是否正确
        HttpSession session = httpServletRequest.getSession();
        String codeStr = (String)session.getAttribute("CodeStr");
        if(codeStr.equals(code)==false){
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("<script>alert(\"验证码错误！\");location.href = \"register.jsp\";</script>");
            writer.flush();
            writer.close();
        }else{
            if(idCard.equals("teacher")) {
                //身份为老师
                //验证码正确后再验证是否已经存在注册用户
                boolean b = TeacherServer.QueryTeacher(username, password);
                if (b == true) {
                    //注册用户已经存在
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.print("<script>alert(\"此用户已经注册！\");location.href = \"register.jsp\";</script>");
                    writer.flush();
                    writer.close();
                } else {
                    //将该信息加入到session中
                    AddTeacherId();
                    teacher teacher = new teacher(teacher_id.toString(), password, username, "男", email);
                    session.setAttribute("info", teacher);
                    //将教师数据加入到数据库
                    List<String> info = new ArrayList<>();
                    info.add(teacher_id.toString());
                    info.add(username);
                    info.add("男");
                    info.add(password);
                    info.add(email);
                    TeacherServer.addTeacherInfoByList(info);
                    httpServletRequest.getRequestDispatcher("/showpagestudentsservlet").forward(servletRequest,servletResponse);
                }
            }else{
                //身份为学生
                //验证码正确后再验证是否已经存在注册用户
                boolean b = StudentServer.QueryStudent(username,password);
                if(b==true){
                    //注册用户已经存在
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.print("<script>alert(\"此用户已经注册！\");location.href = \"register.jsp\";</script>");
                    writer.flush();
                    writer.close();
                }else{
                    AddStudentId();
                    student student = new student(student_id.toString(), password, username, "男","2016-9","数学",email);
                    session.setAttribute("info", student);
                    //将教师数据加入到数据库
                    List<String> info = new ArrayList<>();
                    info.add(student_id.toString());
                    info.add(username);
                    info.add("男");
                    info.add("数学");
                    info.add("2016-9");
                    info.add(password);
                    info.add(email);
                    StudentServer.addStudentByList2(info);
                    httpServletRequest.getRequestDispatcher("/student/main.jsp").forward(servletRequest,servletResponse);
                }
            }
        }
    }
    @Override
    public void destroy() {
    }
    public void AddTeacherId(){
        this.teacher_id=teacher_id.add(new BigInteger("1"));
    }
    public void AddStudentId(){this.student_id=student_id.add(new BigInteger("1"));}
}
