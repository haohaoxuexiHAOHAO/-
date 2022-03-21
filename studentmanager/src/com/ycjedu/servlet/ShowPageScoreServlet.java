package com.ycjedu.servlet;

import com.ycjedu.javabean.score;
import com.ycjedu.server.ScoreServer;
import com.ycjedu.utils.JudgeTypeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowPageScoreServlet extends HttpServlet {
    private final int default_size=10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取key (需要显示成绩的同学id)
        String key = req.getParameter("key");
        if(key==null||key.equals("")){
            //如果没有key默认显示10个人
            int size=default_size;
            int current_idx=-1;
            //请求显示的页面
            String index = req.getParameter("index");
            if(JudgeTypeUtils.JudgeStringIsInteger(index)){
                //如果是数
                current_idx=Integer.parseInt(index);
            }else{
                current_idx=1;
            }

            //通过请求的页面数和请求的学生成绩数得到成绩
            List<score> allStudentsScore = ScoreServer.getAllStudentsScore(current_idx,size);
            if(allStudentsScore!=null){
                //一共有多少人的分数
                int score_count=(int)ScoreServer.getAllScoreCount();
                //获取总共需要多少页面
                int sum_idx=score_count%size==0?score_count/size:score_count/size+1;
                HttpSession session = req.getSession();
                session.setAttribute("allStudentsScore",allStudentsScore);
                session.setAttribute("sum_idx",sum_idx);
                resp.sendRedirect(req.getContextPath()+"/teacher/score.jsp");
            }else{
                System.out.println("未找到");
            }
        }else{
            if(JudgeTypeUtils.JudgeStringIsInteger(key)){
                HttpSession session = req.getSession();
                score scoreById = ScoreServer.getScoreById(key);
                List<score> scores= new ArrayList<score>();
                scores.add(scoreById);
                session.setAttribute("allStudentsScore",scores);
                session.setAttribute("sum_idx",1);
                resp.sendRedirect(req.getContextPath()+"/teacher/score.jsp");
            }else{
                System.out.println("未找到");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
