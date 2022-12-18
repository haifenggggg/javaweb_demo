package com.haifeng;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ResourceBundle;

public class demo1 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        /**
         * 注册驱动 获取连接 获取数据库操作对象 执行sql 处理查询结果集 释放资源
         */
        PrintWriter writer = response.getWriter();
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            Class.forName(driver);
            connection=DriverManager.getConnection(url,user,password);
            statement=connection.createStatement();
            String sql="select sno,sname from t_student";
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String sno = resultSet.getString("sno");
                String sname = resultSet.getString("sname");
                System.out.println(sno+","+sname);
                writer.println(sno+","+sname);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
