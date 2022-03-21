package com.ycjedu.servlet;

import com.ycjedu.javabean.student;
import com.ycjedu.server.StudentServer;
import com.ycjedu.utils.JDBCutilsBydruid;
import com.ycjedu.utils.JudgeTypeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowPageStudentsServlet extends HttpServlet {
    private final int default_size=10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        //得到请求的key (学号或者姓名)
        String key = req.getParameter("key");
        //未传入key
        if(null==key||key.equals("")){
            //请求的学生人数
            int size=default_size;
            //请求显示的页面数
            int current_idx=-1;
            String idx=req.getParameter("index");
            if(idx!=null&&idx.equals("")==false){
                //判断获取的idx是否为数字
                if(JudgeTypeUtils.JudgeStringIsInteger(idx)) {
                    //是数字
                    current_idx = Integer.parseInt(idx);

                }
                //不是数字默认为-1
            }else{
                current_idx=1;
            }
            //通过请求的页面数和请求的学生数获取请求学生
            List<student> allStudents = StudentServer.getAllStudents(current_idx, size);

            if(allStudents!=null) {
                //获取学生的总人数
                int students_count = (int)StudentServer.getAllStudentsCount();
                //获取显示所有学生需要的页数
                int sum_idx = students_count % size == 0 ? students_count / size : students_count / size + 1;
                HttpSession session = req.getSession();
                session.setAttribute("allStudents",allStudents);
                session.setAttribute("sum_idx",sum_idx);
                resp.sendRedirect("/studentmanager/teacher/main.jsp");
            }else{
                System.out.println("未找到");
            }
        }else{
            if(JudgeTypeUtils.JudgeStringIsInteger(key)){
                //key是学号
                //得到该学生
                student student = StudentServer.getStudentById(key);
                //将该学生装入一个List
                List<student> students=new ArrayList<student>();
                students.add(student);
                HttpSession session = req.getSession();
                session.setAttribute("allStudents",students);
                session.setAttribute("sum_idx",1);
                resp.sendRedirect("/studentmanager/teacher/main.jsp");
            }else{
                //key是姓名
                student student = StudentServer.getStudentByName(key);
                List<student> students=new ArrayList<student>();
                students.add(student);
                HttpSession session = req.getSession();
                session.setAttribute("allStudents",students);
                session.setAttribute("sum_idx",1);
                resp.sendRedirect("/studentmanager/teacher/main.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
