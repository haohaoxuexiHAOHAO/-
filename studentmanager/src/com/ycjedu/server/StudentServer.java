package com.ycjedu.server;

import com.ycjedu.dao.StudentDao;
import com.ycjedu.javabean.student;

import java.util.List;

public class StudentServer {
    private static StudentDao studentDao=new StudentDao();

    //查询student表中是否存在某学生
    public static boolean QueryStudent(String username,String password){
        String sql="select name,password from student where name=? and password=?";
        student student = studentDao.QuerySingleRowMoreCol(sql, student.class, username, password);
        if(student!=null&&student.getName().equals(username)&&student.getPassword().equals(password))return true;
        return false;
    }

    //index.jsp中通过cookie中保存的学生名获取学生信息
    public static student QueryStudent(String username){
        String sql="select * from student where name=?";
        return studentDao.QuerySingleRowMoreCol(sql,student.class,username);
    }

    //通过学生的id获取学生信息
    public static student getSingleStudent(String id){
        String sql="select * from student where id=?";
        return studentDao.QuerySingleRowMoreCol(sql,student.class,id);
    }

    //获取一定数量的学生的信息
    /**
     * page:取第几页
     * size:取多少
     * */
    public static List<student> getAllStudents(int page,int size){
            String sql = "select * from student limit ?,?";
            return studentDao.QueryMultiply(sql, student.class, (page - 1) * size, size);
    }

    //获取学生总人数
    public static long getAllStudentsCount(){
        String sql="select count(*) from student";
        return (Long)studentDao.QuerySingleRowSingleCol(sql);
    }

    //通过学号获取学生的信息
    public static student getStudentById(String id){
        String sql="select * from student where id=?";
        return studentDao.QuerySingleRowMoreCol(sql,student.class,id);
    }

    //修改学生的信息
    public static int updateStudentByList(List<String> NewInfo){
        /**
         *         NewInfo.add(studentNo);
         *         NewInfo.add(studentName);
         *         NewInfo.add(studentSex);
         *         NewInfo.add(studentMajor);
         * */
        String sql="update student set name=?,sex=?,major=? where id=?";
        return studentDao.UpdateTable(sql,NewInfo.get(1),NewInfo.get(2),NewInfo.get(3),NewInfo.get(0));
    }

    //通过id删除学生信息
    public static int deleteStudentById(String id){
        String sql="delete from student where id=?";
        return studentDao.UpdateTable(sql,id);
    }

    //加入学生的信息
    public static int addStudentByList(List<String> Info){
        /**
         *         Info.add(studentId);
         *         Info.add(studentName);
         *         Info.add(studentSex);
         *         Info.add(studentMajor);
         *         Info.add(studentDate);
         *
         * */
        String sql="insert into student (id,password,name,sex,school_date,major)values(?,?,?,?,?,?)";
        return studentDao.UpdateTable(sql,Info.get(0),0,Info.get(1),Info.get(2),Info.get(4),Info.get(3));
    }

    //加入学生的信息
    public static int addStudentByList2(List<String> Info){
        /**
         *         Info.add(studentId);
         *         Info.add(studentName);
         *         Info.add(studentSex);
         *         Info.add(studentMajor);
         *         Info.add(studentDate);
         *         Info.add(studentPassword)
         *         Info.add(studentEmail)
         *
         * */
        String sql="insert into student (id,password,name,sex,school_date,major,email)values(?,?,?,?,?,?,?)";
        return studentDao.UpdateTable(sql,Info.get(0),Info.get(5),Info.get(1),Info.get(2),Info.get(4),Info.get(3),Info.get(6));
    }

    //通过学生姓名获取id
    public static String getStudentIdByName(String Name){
        String sql="select id from student where name=?";
        return (String)studentDao.QuerySingleRowSingleCol(sql,Name);
    }

    //通过姓名获得学生信息
    public static student getStudentByName(String name){
        String sql="select * from student where name=?";
        return studentDao.QuerySingleRowMoreCol(sql,student.class,name);
    }

    //通过姓名和密码获取学生信息
    public static student getStudentByNameAndPassWord(String name,String password){
        String sql="select * from student where name=? and password=?";
        return studentDao.QuerySingleRowMoreCol(sql,student.class,name,password);
    }

    //修改学生的密码和邮箱
    public static int updateStudentPassWordAndEmail(String id,String password,String email){
        String sql="update student set password=?,email=? where id=?";
        return studentDao.UpdateTable(sql,password,email,id);
    }

    //获取学生的最大id
    public static String getMaxId(){
        String sql="select id from student order by id desc limit 0,1";
        return (String)studentDao.QuerySingleRowSingleCol(sql);
    }
}
