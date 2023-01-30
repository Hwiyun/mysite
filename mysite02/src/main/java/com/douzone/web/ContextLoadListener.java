package com.douzone.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	String contextconfigLocation = sc.getInitParameter("contextConfigPath");
    	System.out.println("Application Starts....");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    }
	
}
