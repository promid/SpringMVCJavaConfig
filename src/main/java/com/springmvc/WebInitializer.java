package com.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by DBQ on 2017/5/19.
 */
// WebApplicationInitializer是Spring提供用来配置Sevlet3.0+配置的接口,从而实现了代替web.xml的位置. 实现此接口将会自动被SpringServletContainerInitializer(用来启动Servlet3.0容器)获取到
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyMvcConfig.class);
        context.setServletContext(servletContext); // 新建WebApplicationContext, 注册配置类, 并将其和当前 servletContext 关联
        Dynamic servletRegistration = servletContext.addServlet("dispatcher", new DispatcherServlet(context)); // 注册DispatcherServlet
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }
}
