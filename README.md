# mySSM
⚙️ SSM项目，✏️编写好SpringMVC、Spring、Mybatis的配置文件，以及Spring核心IoC和aop的使用✅

# SSM

## 介绍

📝 [(210条消息) SSM框架讲解（史上最详细的文章）_代码贩子、的博客-CSDN博客_ssm框架](https://blog.csdn.net/weixin_45650003/article/details/121623824)

官方Spring+官方SpringMVC+MyBatis

![img](https://img-blog.csdnimg.cn/20200704143030291.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0Nzg1MzUx,size_16,color_FFFFFF,t_70)

**SSM框架简介**

SSM框架是Spring MVC ，Spring和Mybatis框架的整合，是标准的MVC模式，将整个系统划分为View层，Controller层，Service层，DAO层四层，使用Spring MVC负责请求的转发和视图管理，Spring实现业务对象管理，Mybatis作为数据对象的持久化引擎。

**SSM框架各层介绍**

1. 持久层（Mybatis）：Dao层（mapper）
   DAO层：DAO层主要是做数据持久层的工作，负责与数据库进行联络的一些任务都封装在此。

   DAO层的设计首先是设计DAO的接口。

   然后在Spring的配置文件中定义此接口的实现类。

   然后就可在模块中调用此接口来进行数据业务的处理，而不用关心此接口的具体实现类是哪个类，显得结构非常清晰。

   DAO层的数据源配置，以及有关数据库连接的参数都在Spring的配置文件中进行配置。

2. 业务层（Spring）：Service层
   Service层：Service层主要负责业务模块的逻辑应用设计。

   首先设计接口，再设计其实现的类。

   接着再在Spring的配置文件中配置其实现的关联。这样我们就可以在应用中调用Service接口来进行业务处理。

   Service层的业务实现，具体要调用到已定义的DAO层的接口。

   封装Service层的业务逻辑有利于通用的业务逻辑的独立性和重复利用性，程序显得非常简洁。

3. 表现层（springMVC）：Controller层（Handler层）
   Controller层：Controller层负责具体的业务模块流程的控制。

   在此层里面要调用Service层的接口来控制业务流程。

   控制的配置也同样是在Spring的配置文件里面进行，针对具体的业务流程，会有不同的控制器，我们具体的设计过程中可以将流程进行抽象归纳，设计出可以重复利用的子单元流程模块，这样不仅使程序结构变得清晰，也大大减少了代码量。

4. View层

   作用：主要和控制层紧密结合，主要负责前台jsp页面的表示。

**SSM框架各层关系**

- DAO层、Service层这两个层次都可以单独开发，互相的耦合度很低，完全可以独立进行，这样的一种模式在开发大项目的过程中尤其有优势。
- Controller，View层因为耦合度比较高，因而要结合在一起开发，但是也可以看作一个整体独立于前两个层进行开发。这样，在层与层之前我们只需要知道接口的定义，调用接口即可完成所需要的逻辑单元应用，一切显得非常清晰简单。
- Service层是建立在DAO层之上的，建立了DAO层后才可以建立Service层，而Service层又是在Controller层之下的，因而Service层应该既调用DAO层的接口，又要提供接口给Controller层的类来进行调用，它刚好处于一个中间层的位置。每个模型都有一个Service接口，每个接口分别封装各自的业务处理方法。

## SpringMVC配置文件

📝[(210条消息) springMVC和applicationContext中的XML文件配置_gu_gu_bird的博客-CSDN博客_applicationcontext.xml include](https://blog.csdn.net/weixin_44654036/article/details/109502450#:~:text=applicationContext.xml 1 设置scope值是singleton时候，加载spring配置文件时候就会创建单实例对象,2 设置scope值是 prototype 时候，不是在加载spring配置文件时候创建对象，是在调用getBean方法时候创建多实例对象)

前端控制器，所有的请求都会到达这个Servlet，它负责统一调度

```java
@Controller
@Service
@Component
@Mapper
@Repository
request.getRequestDispatcher("viewUser.jsp").forward(request,response);
```

**配置springMVC的前端控制器**  web.xml

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	version="3.1" metadata-complete="true">
     <display-name>Archetype Created Web Application</display-name>  
    <!-- Spring和mybatis的配置文件 -->  
    <context-param>  
        <param-name>contextConfigLocation</param-name>  
        <param-value>classpath:applicationContext.xml</param-value> 
    </context-param>  
    
	<!-- 如果是用mvn命令生成的xml，需要修改servlet版本为3.1 -->
	<!-- 配置DispatcherServlet -->
	<servlet>
		<servlet-name>mvc-dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<!-- 配置springMVC需要加载的配置文件
			spring-dao.xml,spring-service.xml,spring-web.xml
			Mybatis - > spring -> springmvc
		 -->
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<!-- <param-value>classpath:spring/spring-*.xml</param-value -->
            <param-value>spring-mvc.xml</param-value>
		</init-param>
        
         <!-- 配置启动加载,会随着项目启动就加载，值越小优先级越高（值为正整数才有效）(非必要) -->
    	<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>mvc-dispatcher</servlet-name>
		<!-- 默认匹配所有的请求 -->
		<url-pattern>/</url-pattern>
	</servlet-mapping>
</web-app>
```

配置请求过滤器，编码格式设为UTF-8，避免中文乱码（非必要）

```xml
<!-- 配置请求过滤器，编码格式设为UTF-8，避免中文乱码 -->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class> org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

配置Listener自动加载spring的配置文件以及设置spring加载文件的名称及路径，默认情况是加载WEB-INF下的ApplicationContext.xml

```xml
<!-- 配置spring的加载文件名称及路径 -->
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>classpath: spring.xml</param-value>
</context-param>
```

配置HiddenHttpMethodFilter，处理从客户端发送的PUT、DELETE请求 （非必要）

```xml
<!-- 配置HiddenHttpMethodFilter，处理从客户端发送的PUT、DELETE请求 -->
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



📝 [(210条消息) SpringMVC配置文件（spring-mvc.xml）_ACGkaka_的博客-CSDN博客_spring-mvc.xml配置](https://blog.csdn.net/qq_33204709/article/details/81136484)

SpringMVC 主要有以下四个配置：

1. 配置组件扫描，必配，组件扫描会扫描包下的所有的Controller类

```xml
    <!-- 配置组件扫描 -->
    <context:component-scan base-package="controller" />
```

2. 配置MVC注解扫描，必配，和组件扫描搭配，相当于同时配置了HandlerMapping和Controller

```xml
    <!-- 配置MVC注解扫描 -->
	<mvc:annotation-driven />
```

3. 配置视图解析器，选配，视图解析器是为了当html，jsp等前端文件放到WEB-INF/的路径下时，浏览器不能直接访问，就需要使用视图解析器来访问相应的前端文件。

```xml
    <!-- 配置视图解析器， -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/" /><!-- 前缀 -->
		<property name="suffix" value=".jsp" /><!-- 后缀 -->
	</bean>
```

4. 配置拦截器，选配，拦截器是拦截tomcat容器和spring容器之间的交互信息，主要是用于验证用户在访问该网页时是否登陆

```xml
    <!-- 配置拦截器 -->
	<mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/*"/>
			<bean class="interceptors.SomeInterceptor"/>
		</mvc:interceptor>
	</mvc:interceptors>
```

spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" 
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/mvc
	http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">
	<!-- 配置SpringMVC -->
    
    <!-- 自动扫描该包，使SpringMVC认为包下用了@controller注解的类是控制器 -->  
    <context:component-scan base-package="cn.edu.guet.controller" />  
    
	<!-- 1.开启SpringMVC注解模式 -->
	<!-- 简化配置： 
		(1)自动注册DefaultAnootationHandlerMapping,AnotationMethodHandlerAdapter 
		(2)提供一些列：数据绑定，数字和日期的format @NumberFormat, @DateTimeFormat, xml,json默认读写支持 
	-->
	<mvc:annotation-driven />
	
	<!-- 2.静态资源默认servlet配置
		(1)加入对静态资源的处理：js,gif,png
		(2)允许使用"/"做整体映射
	 -->
	 <mvc:default-servlet-handler/>
    
    <!-- 静态资源处理  css js imgs -->
    <mvc:resources location="/resources/**" mapping="/resources"/>
	 
	 <!-- 3.配置jsp 显示ViewResolver  定义跳转的文件的前后缀 ，视图模式配置 -->
	 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 	<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
	 	<property name="prefix" value="/WEB-INF/jsp/" />
	 	<property name="suffix" value=".jsp" />
	 </bean>
	 
</beans>
```

## spring配置文件

📝 [怎样使用Spring的配置文件？带大家一起玩转Spring配置文件-云社区-华为云 (huaweicloud.com)](https://bbs.huaweicloud.com/blogs/327394)

🧷 https://github.com/THEO-1126/ssm/blob/master/src/main/resources/spring/spring-dao.xml

applicationContext.xml

实际开发中，Spring的配置内容非常多，这就导致Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，而在Spring主配置文件通过import标签进行加载

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd">
    
    <!-- resource="路径"，写出要加载的xml文件的相对路径 -->
    <import resource="spring-dao.xml"/>
    <import resource="spring-service.xml"/>
</beans>
```

spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd">
	<!-- 配置整合mybatis过程 -->
	<!-- 1.配置数据库相关参数properties的属性：${url} -->
	<context:property-placeholder location="db.properties" />

	<!-- 2.数据库连接池 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<!-- 配置连接池属性 -->
		<property name="driverClass" value="${driver}" />
		<property name="jdbcUrl" value="${url}" />
		<property name="user" value="${user}" />
		<property name="password" value="${password}" />

		<!-- c3p0连接池的私有属性 -->
		<property name="maxPoolSize" value="30" />
		<property name="minPoolSize" value="10" />
		<!-- 关闭连接后不自动commit -->
		<property name="autoCommitOnClose" value="false" />
		<!-- 获取连接超时时间 -->
		<property name="checkoutTimeout" value="10000" />
		<!-- 当获取连接失败重试次数 -->
		<property name="acquireRetryAttempts" value="2" />
	</bean>
    
    <!-- 或者使用数据库 -->
    <bean>
        <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
        <property name="url" value="${url}"></property>
        <property name="username" value="${name}"></property>
        <property name="password" value="${password}"></property>
    </bean>

	<!-- 3.配置SqlSessionFactory对象 -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 注入数据库连接池 -->
		<property name="dataSource" ref="dataSource" />
		<!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
		<property name="configLocation" value="classpath:mybatis-config.xml" />
		<!-- 扫描entity包 使用别名 -->
		<property name="typeAliasesPackage" value="com.soecode.lyf.entity" />
		<!-- 扫描sql配置文件:mapper需要的xml文件 -->
		<property name="mapperLocations" value="classpath:mapper/*.xml" />
	</bean>

	<!-- 4.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<!-- 注入sqlSessionFactory -->
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
		<!-- 给出需要扫描Dao接口包 -->
		<property name="basePackage" value="com.soecode.lyf.dao" />
	</bean>
</beans>
```

spring-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/tx
	http://www.springframework.org/schema/tx/spring-tx.xsd">
	<!-- 扫描service包下所有使用注解的类型 -->
	<context:component-scan base-package="com.soecode.lyf.service" />

	<!-- 配置事务管理器 -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<!-- 注入数据库连接池 -->
		<property name="dataSource" ref="dataSource" />
	</bean>

	<!-- 配置基于注解的声明式事务 -->
	<tx:annotation-driven transaction-manager="transactionManager" />
</beans>
```



依赖 pom.xml

```xml
<dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
	<!-- 数据库连接池 -->
    <dependency>
        <groupId>c3p0</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.1.2</version>
    </dependency>
  </dependencies>


<build>
    <finalName>OfficialSpringMVC</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

# mySSM

## 架构

![img](https://img-blog.csdnimg.cn/20200704143030291.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0Nzg1MzUx,size_16,color_FFFFFF,t_70)

## SpringMVC 配置文件

**dispatcherServlet.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/mvc
	http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">


    <!-- 扫描controller包 -->
    <context:component-scan base-package="cn.edu.guet.controller" />

    <!-- 配置SpringMVC -->
    <!-- 1.开启SpringMVC注解模式 -->
    <!-- 简化配置：
        (1)自动注册DefaultAnootationHandlerMapping,AnotationMethodHandlerAdapter
        (2)提供一些列：数据绑定，数字和日期的format @NumberFormat, @DateTimeFormat, xml,json默认读写支持
    -->
    <mvc:annotation-driven />

    <!-- 2.静态资源默认servlet配置
        (1)加入对静态资源的处理：js,gif,png
        (2)允许使用"/"做整体映射
     -->
    <mvc:default-servlet-handler/>

    <!-- 3.配置jsp 显示ViewResolver -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>

</beans>
```

**springMVC的前端控制器 web.xml** 

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1" metadata-complete="true">
  <display-name>Archetype Created Web Application</display-name>
  <!-- Spring 配置文件 -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!-- 如果是用mvn命令生成的xml，需要修改servlet版本为3.1 -->
  <!-- 配置DispatcherServlet -->
  <servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 配置springMVC需要加载的配置文件
        spring-dao.xml,spring-service.xml,spring-web.xml
        Mybatis - > spring -> springmvc
     -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:dispatcherServlet.xml</param-value>
    </init-param>


    <!-- 配置启动加载,会随着项目启动就加载，值越小优先级越高（值为正整数才有效）(非必要) -->
    <load-on-startup>1</load-on-startup>

  </servlet>

  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <!-- 默认匹配所有的请求 -->
    <url-pattern>/</url-pattern>
  </servlet-mapping>
</web-app>

```

## Spring配置文件

**applicationContext.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        https://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- resource="路径"，写出要加载的xml文件的相对路径 -->

    <import resource="spring-dao.xml"/>
    <import resource="spring-service.xml"/>

</beans>

```

**spring-dao.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 配置整合mybatis过程 -->
    <!-- 1.配置数据库相关参数properties的属性：${url} -->
    <context:property-placeholder location="classpath:db.properties" />

    <!-- 2.数据库连接池 -->
<!--    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">-->
<!--        &lt;!&ndash; 配置连接池属性 &ndash;&gt;-->
<!--        <property name="driverClass" value="${driver}" />-->
<!--        <property name="jdbcUrl" value="${url}" />-->
<!--        <property name="user" value="${name}" />-->
<!--        <property name="password" value="${password}" />-->

<!--        &lt;!&ndash; c3p0连接池的私有属性 &ndash;&gt;-->
<!--        <property name="maxPoolSize" value="30" />-->
<!--        <property name="minPoolSize" value="10" />-->
<!--        &lt;!&ndash; 关闭连接后不自动commit &ndash;&gt;-->
<!--        <property name="autoCommitOnClose" value="false" />-->
<!--        &lt;!&ndash; 获取连接超时时间 &ndash;&gt;-->
<!--        <property name="checkoutTimeout" value="10000" />-->
<!--        &lt;!&ndash; 当获取连接失败重试次数 &ndash;&gt;-->
<!--        <property name="acquireRetryAttempts" value="2" />-->
<!--    </bean>-->

    <!-- 2. 或者使用数据库 -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="${driver}"></property>
        <property name="url" value="${url}"></property>
        <property name="username" value="${name}"></property>
        <property name="password" value="${password}"></property>
    </bean>

    <!-- 3.配置SqlSessionFactory对象 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 注入数据库连接池 -->
        <property name="dataSource" ref="dataSource" />
        <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
<!--        <property name="configLocation" value="classpath:mybatis-config.xml" />-->
        <!-- 扫描bean包-->
        <property name="typeAliasesPackage" value="cn.edu.guet.bean" />
        <!-- 扫描sql配置文件:mapper需要的xml文件 -->
        <property name="mapperLocations" value="classpath:mapper/*.xml" />
    </bean>

    <!-- 4.配置扫描Dao接口包，动态实现Dao接口，注入到spring容器中 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 注入sqlSessionFactory -->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
        <!-- 给出需要扫描Dao接口包 -->
        <property name="basePackage" value="cn.edu.guet.dao" />
    </bean>
</beans>
```

**spring-service**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd
	http://www.springframework.org/schema/tx
	http://www.springframework.org/schema/tx/spring-tx.xsd">

    <import resource="spring-dao.xml"/>

    <!-- 扫描service包下所有使用注解的类型  @Autowired 自动注入-->
    <context:component-scan base-package="cn.edu.guet.service" />

    <!-- 配置事务管理器 -->
    <bean id="transactionManager"
          class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!-- 注入数据库连接池 -->
        <property name="dataSource" ref ="dataSource" />
    </bean>

    <!-- 配置基于注解的声明式事务 -->
    <tx:annotation-driven transaction-manager="transactionManager" />
</beans>
```

## mybatis

**mybatis-config.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 配置全局属性 -->
    <settings>
        <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->
        <setting name="useGeneratedKeys" value="true" />

        <!-- 使用列别名替换列名 默认:true -->
        <setting name="useColumnLabel" value="true" />

        <!-- 开启驼峰命名转换:Table{create_time} -> Entity{createTime} -->
        <setting name="mapUnderscoreToCamelCase" value="true" />
    </settings>
</configuration>
```

**db.properties**

```xml
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/redis_test?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
name=root
password=THEO1126
```

## 日志

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <!-- 配置日志信息输出目的地 -->
    <Appenders>
        <!-- 输出到控制台 -->
        <Console name="Console" target="SYSTEM_OUT">
            <!--配置日志信息的格式 -->
            <PatternLayout
                    pattern="%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n"/>
            "/>
        </Console>

        <!-- 输出到文件，其中有一个append属性，默认为true，即不清空该文件原来的信息，采用添加的方式，若设为false，则会先清空原来的信息，再添加 -->
        <File name="MyFile" fileName="/Users/liwei/mybatis.log" append="false">
            <PatternLayout>
                <!--配置日志信息的格式 -->
                <pattern>%d{HH:mm:ss} [%t] %-5level %logger{36} - %msg%n</pattern>
            </PatternLayout>
        </File>

    </Appenders>
    <!-- 定义logger,只有定义了logger并引入了appender,appender才会有效 -->
    <Loggers>
        <!-- 将业务mapper接口所在的包填写进去,并用在控制台和文件中输出 -->
        <logger name="cn.edu.guet.dao" level="TRACE" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="MyFile"/>
        </logger>

        <Root level="INFO">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="MyFile"/>
        </Root>
    </Loggers>
</Configuration>
```

## 依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>cn.edu.guet</groupId>
  <artifactId>mySSM</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>mySSM Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <!-- 1.日志 -->
    <!-- 实现slf4j接口并整合 -->
    <dependency>
      <groupId>ch.qos.logback</groupId>
      <artifactId>logback-classic</artifactId>
      <version>1.1.1</version>
    </dependency>

    <!-- 2.数据库 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.21</version>
    </dependency>

    <dependency>
      <groupId>c3p0</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.1.2</version>
    </dependency>

    <!-- DAO: MyBatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.3.0</version>
    </dependency>
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.2.3</version>
    </dependency>

    <!-- 3.Servlet web -->
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.5.4</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>

    <!-- 4.Spring -->
    <!-- 1)Spring核心 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.24</version>
    </dependency>
    <!-- 2)Spring DAO层 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.3.24</version>
    </dependency>
    <!-- 3)Spring web -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.24</version>
    </dependency>
    <!-- 4)Spring test -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>4.1.7.RELEASE</version>
    </dependency>
    <!--  Spring aop  -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>5.3.24</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.3.24</version>
    </dependency>

    <!-- redis客户端:Jedis -->
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>2.7.3</version>
    </dependency>
    <dependency>
      <groupId>com.dyuproject.protostuff</groupId>
      <artifactId>protostuff-core</artifactId>
      <version>1.0.8</version>
    </dependency>
    <dependency>
      <groupId>com.dyuproject.protostuff</groupId>
      <artifactId>protostuff-runtime</artifactId>
      <version>1.0.8</version>
    </dependency>
     <!--  日志   -->
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.17.1</version>
    </dependency>
    <!--  JSON 依赖  -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.9.3</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.9.3</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.3</version>
    </dependency>
    <!-- Map工具类 -->
    <dependency>
      <groupId>commons-collections</groupId>
      <artifactId>commons-collections</artifactId>
      <version>3.2</version>
    </dependency>

  </dependencies>

  <build>
    <finalName>mySSM</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```





## 问题

**问题1**

问题：解决 Could not open ServletContext resource [/jdbc.properties]

![image-20230114233435667](C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20230114233435667.png)

解决：

[ 解决 Could not open ServletContext resource /jdbc.properties\]_代码深耕的博客-CSDN博客](https://blog.csdn.net/m0_57310021/article/details/122651991)

ClassPath指定的是java加载类的路径。只有类在ClassPath中，java命令才能找到它，并解释它。

![image-20230114233445928](C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20230114233445928.png)

**问题2**

Error creating bean with name ‘dataSource‘ defined in class path resource报错解决方法

![image-20230114233510741](C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20230114233510741.png)

解决：

[(210条消息) Error creating bean with name ‘dataSource‘ defined in class path resource报错解决方法_tkrj7_的博客-CSDN博客_error creating bean with name 'datasourcescriptdat](https://blog.csdn.net/tkrj7_/article/details/115629939)

1. aspactj的jar包没导入进去

   ```xml
   <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-aspects</artifactId>
           <version>5.2.5.RELEASE</version>
   </dependency>
   
   ```

2. mysql-connector-java的版本问题 修改为最新版本：

   ```xml
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.21</version>
   </dependency>
   ```

**问题3**

No Spring WebApplicationInitializer types detected on classpath 加载不到配置文件或找不到spring主配置

解决：

1. 在web.xml当中配置错误，没有正确的引入spring的配置文件。找不到时，报告次错误

   检查配置文件，引入是否正确。

   classpath:springconfig.xml ,这个格式的配置为读取本项目classpath下的文件

   classpath*:springconfig.xml 这个格式的配置为读取本项目下或者是jar包的classpath下的配置文件。多个同名只读第一个。

2. No Spring WebApplicationInitializer types detected on classpath 

   tomacat 服务器加载jar 不全 ，导致启动错误。

   使用编译器 maven clean  下。在clean 下项目。

3. lag4j.properties 配置文件的加载 

   这个配置文件一般要配置到当前目录中。web.xml 当中引入日志配置文件时，使用classpath*:log4j.properties 配置参数，如果文件在jar包，会报错：找不到此文件。

**问题4 **

java.lang.ClassNotFoundException: com.fasterxml.jackson.databind.exc.InvalidDefinitionException

![image-20230115003458997](C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20230115003458997.png)

解决：

[(210条消息) 解决java.lang.ClassNotFoundException: com.fasterxml.jackson.databind.exc.InvalidDefinitionException_开着拖拉机回家的博客-CSDN博客](https://blog.csdn.net/qq_35995514/article/details/90263116)

加入json依赖

```xml
<!--  JSON 依赖  -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.9.3</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.9.3</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.3</version>
</dependency>
<!-- Map工具类 -->
<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2</version>
</dependency>
```

