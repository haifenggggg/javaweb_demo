1.curd
c:create 增  r：Retrieve(检索)查   u:update改  d:delete删
2.insert
  <insert id="abcd">
       insert into t_car(car_num,brand,guide_price,produce_time,car_type)
     values ("1222","凯迪拉克CT5",90,"2022-12-12","燃油车")
   </insert>
   这样写的问题是：值 显然写死到配置文件当中 ，这个在实际开发中是不存在的，一定是前端的form表单提交过来数据，然后传值给sql语句
   例如：jdbc的代码是怎么写的
   insert into t_car(car_num,brand,guide_price,produce_time,car_type)
        values (?,?,?,?,?)
    ps.setString(1,xxx)
    ps.setString(2,yyy)
    ...
  在jdbc中占位符是？，在mybatis中占位符是什么呢
  和？等效的写法是#{}
  在mybatis当中不能使用？占位符，必须使用#{}来代替jdbc当中的？
  #{}和jdbc当中的？是等价的
  *map集合传值，则#{}里面是key值
  *java程序中使用pojo类给sql语句的占位符传值，注意：占位符#{}，大括号里面写：pojo类的属性名
  严格意义上来说：如果使用pojo对象传递值的话。#{}这个大括号到底些什么
  写的是：get方法的方法名去掉get,然后将剩下的单词首字母小写，然后放进去
  例如：getUsername()-->#{username}
  例如：getEmail()-->#{email}
  ..

