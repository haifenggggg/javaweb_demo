package com.haifeng.mybatisdemo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class demo1 {
    public static void main(String[] args)  {
        //SqlSessionFactory
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream= null;
        SqlSession sqlSession=null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
             sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("abcd");
        } catch (IOException e) {
            e.printStackTrace();
            if(sqlSession!=null){
                sqlSession.rollback();
            }

        }finally {
            sqlSession.commit();
            sqlSession.close();
        }


    }
}
