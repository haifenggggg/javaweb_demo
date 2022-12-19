package com.haifeng.mybatis.test;

import com.haifeng.mybatis.pojo.Car;
import com.haifeng.mybatis.utils.SqlSessionUnit;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class demotest {
    @Test
    public void tt(){
        SqlSession sqlSession = SqlSessionUnit.openSession();
        List<Object> list = sqlSession.selectList("qqq");
        list.forEach(car->System.out.println(car));
        sqlSession.close();
    }
    @Test
    public void vv(){
        SqlSession sqlSession = SqlSessionUnit.openSession();
        Object ddd = sqlSession.selectOne("ddd", 1l);
        System.out.println(ddd.toString());
        sqlSession.close();
    }
    @Test
    public void bb(){
        Car car=new Car(19l,"1221","保时捷",100,"2024-2-23","电动车");
        SqlSession sqlSession=SqlSessionUnit.openSession();
        sqlSession.update("qaz",car);
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void hh(){
        SqlSession sqlSession = SqlSessionUnit.openSession();
        sqlSession.delete("qwer",17);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void gg(){
        Car car=new Car(null,"2343","宝马325li",30,"2023-3-4","电动车");
        SqlSession sqlSession = SqlSessionUnit.openSession();
        sqlSession.insert("abcd",car);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void ss(){
        Map<String,Object> map=new HashMap<>();
        map.put("k1","1234");
        map.put("k2","奥迪a7");
        map.put("k3",70);
        map.put("k4","2022-21-22");
        map.put("k5","燃油车");
        SqlSession sqlSession = SqlSessionUnit.openSession();
        sqlSession.insert("abcd",map);
        sqlSession.commit();
        sqlSession.close();

    }
}
