# springboot-shiro-cas-mybatis
参考来源-https://github.com/zzuhub/sso-client-rep.git    【cas-shiro-redis-mybatis-springboot】

这个是一个springboot工程

配置了Windows  C:\Windows\System32\drivers\etc\hosts下面
127.0.0.1 www.cas.com   模拟cas认证中心
127.0.0.1 www.ssoclient.com   cas客户端

blog是一些网上参考的博客

cas-4.1.0是sso认证服务端的官方代码

sso-client-rep-master是客户端shiro-cas(1.2.4)的代码-使用了Redis作为shiro的缓存,【但是没有实现app接入和rest登录，没有实现单点登录登出！】
shiroTest 仅仅是测试shiro在Springboot里面集成！
springboot_cas_shiro_forkUse_jpa是客户端shiro-cas(1.2.4)的代码-jpa数据层，实现单点登录登出，【但是没有实现app接入和rest登录！】
client_shiro_cas_pac4j-不使用shiro-cas(1.2.4)，rest登录换成pac4j,实现单点登录登出，实现app接入和rest登录！

springboot_shiro_cas_forkUse_jpa :原作地址：https://github.com/maojun2016/springboot_cas_shiro

testredisshiro.sql是数据库代码-mysql----/cas-4.1.0/cas-server-webapp/src/main/webapp/WEB-INF/deployerConfigContext.xml------md5加密
【<!-- 通过数据库验证身份，这个得自己去实现 admin  admin -->
	<bean id="primaryAuthenticationHandler"
		class="org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler"
		p:dataSource-ref="dataSource" p:passwordEncoder-ref="passwordEncoder"
		p:sql="select password from tb_user where username = ? and active = 1" />

	<!-- 设置数据源 -->
	<!-- <bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
		<property name="url"
			value="jdbc:mysql://127.0.0.1:3306/db_test?useUnicode=true&amp;characterEncoding=utf8"></property>
		<property name="username" value="root"></property>
		<property name="password" value="HSJissmart"></property>
	</bean> -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName">
			<value>com.mysql.jdbc.Driver</value>
		</property>
		<property name="url" 
		          value="jdbc:mysql://127.0.0.1:3306/testredisshiro?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true">
		</property>
		<property name="username" value="root" />
		<property name="password" value="HSJissmart" />
		<property name="initialSize" value="10" />
		<property name="maxActive" value="500" />
		<property name="maxWait" value="60000" />
		<property name="validationQuery">
			<value>select 1</value>
		</property>
	</bean>】


controller是一些测试获取信息

RestController.java是模拟rest登录获取tgt和st





说明：
待完成cas 认证中心返回更多信息！！！！
经典教程
参考博客：http://m.blog.csdn.net/u010475041/article/details/78140643
github:https://github.com/kawhii/sso

http://blog.csdn.net/a7695895/article/details/53262494

