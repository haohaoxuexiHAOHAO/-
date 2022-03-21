package com.ycjedu.server;

import com.ycjedu.dao.TeacherDao;
import com.ycjedu.javabean.teacher;

import java.util.List;

public class TeacherServer {
    private static TeacherDao teacherDao=new TeacherDao();


    //通过姓名查询老师的id
    public static String getTeacherIdByName(String name){
        String sql="select id from teacher where name=?";
        return (String)teacherDao.QuerySingleRowSingleCol(sql,name);
    }

    //通过id来查询老师
    public static teacher getTeacherById(String id){
        String sql="select * from teacher where id=?";
        return teacherDao.QuerySingleRowMoreCol(sql,teacher.class,id);
    }

    //查询teacher表中是否存在某老师
    public static boolean QueryTeacher(String username,String password){
        String sql="select name,password from teacher where name=? and password=?";
        teacher teacher = teacherDao.QuerySingleRowMoreCol(sql, teacher.class, username, password);
        if(teacher!=null&&teacher.getName().equals(username)&&teacher.getPassword().equals(password))return true;
        return false;
    }

    //在index.jsp中通过cookie中保存的教师名获取教师信息
    public static teacher QueryTeacher(String username){
        String sql="select * from teacher where name=?";
        return teacherDao.QuerySingleRowMoreCol(sql, teacher.class, username);
    }

    //通过姓名和密码获取信息
    public static teacher getTeacherByNameAndPwd(String name,String pwd){
        String sql="select * from teacher where name=? and password=?";
        return teacherDao.QuerySingleRowMoreCol(sql,teacher.class,name,pwd);
    }

    //将老师更新后信息存入数据库
    public static int updateTeacherByList(List<String>Info){
        /**
         *         Info.add(teacherId);
         *         Info.add(teacherName);
         *         Info.add(teacherSex);
         *         Info.add(teacherPassword);
         *         Info.add(teacherEmail);
         *
         * */
        String sql="update teacher set name=?,password=?,sex=?,email=? where id=?";

        return teacherDao.UpdateTable(sql,Info.get(1),Info.get(3),Info.get(2),Info.get(4),Info.get(0));
    }

    //将老师的信息存入数据库
    public static int addTeacherInfoByList(List<String> info){
        String sql="insert into teacher values(?,?,?,?,?)";
        return teacherDao.UpdateTable(sql,info.get(0),info.get(3),info.get(1),info.get(2),info.get(4));

    }

    //查询当前数据库中老师的最大id
    public static String getMaxId(){
        String sql="select id from teacher order by id desc limit 0,1";
        return (String)teacherDao.QuerySingleRowSingleCol(sql);
    }

    //通过id更新老师的密码
    public static int UpdateTeacherPassWordById(String id,String password){
        String sql="update teacher set password=? where id=?";
        return teacherDao.UpdateTable(sql,password,id);
    }
}
