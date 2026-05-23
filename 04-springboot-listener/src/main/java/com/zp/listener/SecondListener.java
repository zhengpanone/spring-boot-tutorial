package com.zp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SecondListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Second Listener init...................");
    }
}
