package com.zp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * SpringBoot 整合Listener
 * <listener>
 *  <listener-class>com.zp.listener.FirsListener</listener-class>
 * </listener>
 */
@WebListener
public class FirstListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Listener init...................");
    }

}
