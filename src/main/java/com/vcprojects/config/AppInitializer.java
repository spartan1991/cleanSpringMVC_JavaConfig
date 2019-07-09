package com.vcprojects.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext annWebAppCtx = new AnnotationConfigWebApplicationContext();

        annWebAppCtx.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(annWebAppCtx));

        annWebAppCtx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(annWebAppCtx));

        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
