package com.ycjedu.server;

import com.ycjedu.dao.ScoreDao;
import com.ycjedu.javabean.score;

import java.util.List;

public class ScoreServer {
    private static ScoreDao scoreDao=new ScoreDao();

    //查询所有同学的成绩
    public static List<score> getAllStudentsScore(){
        String sql="select * from score";
        return scoreDao.QueryMultiply(sql,score.class);
    }

    //查询总共多少份分数
    public static long getAllScoreCount(){
        String sql="select count(*) from score";
        return (Long)scoreDao.QuerySingleRowSingleCol(sql);
    }

    //通过page和size获取部分分数
    public static List<score> getAllStudentsScore(int page,int size){
        String sql="select * from score limit ?,?";
        return scoreDao.QueryMultiply(sql,score.class,(page-1)*size,size);
    }

    //通过学号得到成绩
    public static score getScoreById(String id){
        String sql="select * from score where id=?";
        return scoreDao.QuerySingleRowMoreCol(sql,score.class,id);
    }

    //通过学生的id来删除学生成绩
    public static int deleteScoreById(String id){
        String sql="delete from score where id=?";
        return scoreDao.UpdateTable(sql,id);
    }

    //通过学生id来加入成绩
    public static int addScoreById(String id){
        String sql="insert into score (id)values(?)";
        return scoreDao.UpdateTable(sql,id);
    }

    //修改学生的成绩
    public static int updateScoreByList(List<String> Info){
        /**
         *         Info.add(id);
         *         Info.add(database);
         *         Info.add(android);
         *         Info.add(jsp);
         * */
        String sql="update score set dat=?,android=?,jsp=? where id=?";
        return scoreDao.UpdateTable(sql,Info.get(1),Info.get(2),Info.get(3),Info.get(0));
    }
}
