package com.rrtyui.listener;

import com.rrtyui.dto.MatchScoreModel;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map<UUID, MatchScoreModel> ongoingMatches = new ConcurrentHashMap<>();
        ServletContext context = sce.getServletContext();
        context.setAttribute("ongoingMatches", ongoingMatches);
    }
}
