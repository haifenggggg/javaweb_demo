package com.haifeng.unit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUnit {

    private SqlSessionUnit() {
    }
    private static SqlSessionFactory sqlSessionFactory;
    static {


       SqlSessionFactoryBuilder  sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static SqlSession openSession(){




          SqlSession  sqlSession = sqlSessionFactory.openSession();
            return sqlSession;


    }
}
