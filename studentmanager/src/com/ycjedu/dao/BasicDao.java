package com.ycjedu.dao;

import com.ycjedu.utils.JDBCutilsBydruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<T> {
    private QueryRunner queryRunner=new QueryRunner();
    private Connection connection;

    //增删改任意表
    public int UpdateTable(String sql,Object... parameters){
        try {
            connection=JDBCutilsBydruid.getConnection();
            return queryRunner.update(connection,sql,parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    //查询任意表的多行多列
    public List<T> QueryMultiply(String sql,Class<T>clazz,Object... parameters){
        try {
            connection=JDBCutilsBydruid.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCutilsBydruid.close(null,null,connection);
        }
        return null;
    }

    //查询任意表的单行多列
    public T QuerySingleRowMoreCol(String sql,Class<T>clazz,Object... parameters){
        try {
            connection=JDBCutilsBydruid.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutilsBydruid.close(null,null,connection);
        }
        return null;
    }

    //查询任意表单行单列
    public Object QuerySingleRowSingleCol(String sql,Object... parameters){
        try {
            connection=JDBCutilsBydruid.getConnection();
            return queryRunner.query(connection,sql,new ScalarHandler(),parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCutilsBydruid.close(null,null,connection);
        }
        return null;
    }
}
