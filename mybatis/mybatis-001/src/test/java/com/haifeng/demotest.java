package com.haifeng;

import com.haifeng.unit.SqlSessionUnit;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class demotest {
    @Test
    public void demm(){
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
    @Test
    public void dd(){
        SqlSession sqlSession = SqlSessionUnit.openSession();
        sqlSession.insert("abcd");
        sqlSession.commit();
        sqlSession.close();
    }
}
