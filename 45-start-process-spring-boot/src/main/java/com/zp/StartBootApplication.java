package com.zp;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@Import(value = DispatcherServlet.class)
@ComponentScan("com.zp")
public class StartBootApplication {

	public static void main(String[] args) throws LifecycleException {
		// 初始化一个容器
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		// 手动注册一个bean
		context.register(StartBootApplication.class);
		// 刷新容器
		context.refresh();
		// 构建tomcat容器
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8888);
		tomcat.addWebapp("/","./");
		tomcat.start();
		// 因为tomcat.start()是非阻塞的，所以需要阻塞下，避免服务停止
		tomcat.getServer().await();


	}

}
