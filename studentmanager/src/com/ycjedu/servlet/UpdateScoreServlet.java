package com.ycjedu.servlet;

import com.ycjedu.server.ScoreServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //得到修改后的成绩
        //这里得到的是当前页面所有人的id和各科成绩
        String[] ids = req.getParameterValues("id");
        String[] databases = req.getParameterValues("database");
        String[] androids = req.getParameterValues("android");
        String[] jsps = req.getParameterValues("jsp");

        //循环修改一遍
        for(int i=0;i<ids.length;i++){
            List<String> Info=new ArrayList<String>();
            Info.add(ids[i]);
            Info.add(databases[i]);
            Info.add(androids[i]);
            Info.add(jsps[i]);
            ScoreServer.updateScoreByList(Info);
        }


        resp.sendRedirect(req.getContextPath()+"/showpagescoreservlet");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
