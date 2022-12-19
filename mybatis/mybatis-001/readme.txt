1.resource目录
放在这个目录中的，一般是资源文件，配置文件，直接放在resource目录下的,等同于放到了类的根路径下
2.开发步骤
*第一步，打包方式jar
*第二步，引入依赖，mybatis依赖  mysql依赖
*第三步，编写mybatis核心配置文件，mybatis-config.xml
 注意：第一：不是非得是mybatis-config.xml这个名字，只是大家都这么叫，第二：这个文件位置也可以随意，但一般情况下会放在根路径下
*第四步：编写xxxMapper.xml文件
这个配置文件当中编写sql语句，这个文件名不固定，放的位置也不固定，这里给他起个名字叫CarMapper.xml,把它暂时放到根路径下
*第五步，在mybatis-config.xml中指定xxxMapper.xml文件路径
<mapper resource="CarMapper.xml"> 注意：resource属性会自动从根路径下开始查找
*第六步：编写mybatis程序(使用mybatis的类库，编写mybatis程序，连接数据库，做增删改查就行了)
在mybatis当中，负责执行sql语句的对象是什么  sqlSession
SqlSession是专门用来执行sql语句的，是一个java程序和数据库的一次会话 ， 想要获取SqlSession对象，需要获取SqlSessionFactory对象，通过SqlSessionFactory工厂来生产SqlSession对象
怎么获取SqlSessionFactory对象？首先要SqlSessionFactoryBuilder对象，通过SqlSessionFactoryBilder对象的build的方法，来获取一个SqlSessionFactory对象
mybatis的核心对象包括：SqlSessionFactoryBuilder SqlSessionFactory SqlSession
SqlSessionFactoryBuilder -->SqlSessionFactory -->SqlSession

3.从xml中构建SqlSessionFactory
通过这句话，第一：在mybatis中有一个重要的对象，这个对象是SqlSessionFactory对象，第二：SqlSessionFactory对象的创建需要xml
xml是什么，他是一个配置文件
4.mybatis中有2个主要的配置文件：
一个是：mybatis-config.xml.这是核心配置文件，主要配置连接数据库信息
另一个是：xxxxMapper.xml ，他是专门用来编写sql语句的配置文件，一个表一个 t_user表 一般会对应一个UserMapper.xml
5.关于第一个程序的小细节
*mybatis中sql的结尾“;” 可以省略
*Resource.getResourceAsStream  小技巧：以后凡是遇到resource这个单词，大部分情况下，这种加载资源的方式都是从根路径下开始加载的（开始查找）
优点：采用这种方式，从类路径当中加载资源，项目的移植性很强，项目从window移植到linux,代码不需要修改，因为这个资源文件一直在类路径当中
*InputStream is=new FileInputStream("d:\\mybatis-config.xml");
采用这种方法也可以，确实就是移植性太差，程序不够健壮，可能会移植到其他操作系统当中，导致以上路径失效，还需要修改Java代码中的路径，这样违背了ocp原则（尽量不修改源代码）
*已经验证了：mybatis核心配置文件的名字，不一定是，mybatis-config.xml,也可以是其他名字
mybatis核心配置文件存放路径，不一定是在类的根路径下，可以放在其他位置，但为了程序的移植性，健壮性。最好将配置文件放在类的根路径下
*InputStream is=ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
ClassLoader.getSystemClassLoader() 获取系统的类加载器     系统类加载器有一个方法叫：getResourceAsStream  他就是从类路径当中加载资源的
通过源代码分析：InputStream is=Resources.getResourceAsStream("mybatis-config.xml")
底层的源代码其实就是：
InputStream is=ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
*CarMapper.xml文件的名字是固定的吗，CarMapper.xml文件的路径是固定的M?  都不是固定的
<mapper resource="CarMapper.xml"> resource属性：这种方式是从类路径当中加载资源
<mapper url="file:///d:/CarMapper.xml“/>url属性：这种方式是从绝对路径当中加载资源
6.事务
*在mybatis-config.xml文件中，可以通过以下的配置进行mybatis的事务管理  <transactionManager type="JDBC">
*type属性的值包括俩个：JDBC(jdbc)  MANAGED(managed) type后面的值，只有以上俩个值可选，不区分大小写
*在mybatis中提供了俩种事务管理机制：第一种：JDBC事务管理器 第二种：MANAGED事务管理器
*JDBC事务管理器：mybatis框架自己管理事务，自己采用原生的jdbc代码去管理事务：conn.setAutoCommit(false);开启事务 。。。。业务处理。。conn.commit();手动提交事务
使用JDBC事务管理器的话，底层创建的事务管理对象：JdbcTransaction对象。
如果你编写的代码是下面的代码：
SqlSession sqlSession=sqlSessionFactory.openSession(true);
表示没有开启事务，因为这种方式压根不会执行：conn.setAutoCommit(false)
在JDBC事务中，没有执行conn.setAutoCommit(false);那么autoCommit就是true
如果autoCommit是true,就表示没有开启事务，只要执行任意一条dml语句就提交一次

*MANAGED事务管理器：
mybatis不再负责事务的管理，事务管理交给其他容器来负责，例如：spring.我不管事务l,你来负责把
对于我们当前的单纯的只有mybatis的情况下，如果配置为：MANAGED
那么事务这一块是没人管的，没有人管理事务表示事务压根没有开启
*JDBC中事务：如果你没有在jdbc代码中执行：conn.setAutoCommit(false);的话，默认的autoCommit是true

重点：以后注意了，只要你的autoCommit是true,就表示没有开启事务，
只有你的autoCommit是false的时候，就表示开启了事务
7.关于mybati集成日志组件，让我们调试起来更加方便
*mybatis常见的日志组件有哪些，slf4j  log4j  log4j2  STDOUT_LOGGING ...注意：log4j log4j2  logback 都是同一个作者开发的
*其中STDOUT_LOGGING是标准日志，mybatis已经实现了这种标准日志，mybatis框架本身已经实现了这种标准，只要开启即可，怎么开启
在mybatis-config.xml文件中使用settings标签配置开启的
<settings>
<setting name="logImpl" value="STDOUT_LOGGING">
<settings>
这个标签在编写的时候要注意，他应该出现在environment 标签之前，注意顺序，当然不需要记忆这个顺序
因为dtd文件会进行约束，我们只要参考dtd约束即可
这种实现也是可以的，可以看到一些信息，比如：连接对象什么时候创建，什么时候关闭，sql语句是怎么样的
但是没有详细的日期，线程名字，等，如果想要丰富的配置，可以集成第三方log组件








































