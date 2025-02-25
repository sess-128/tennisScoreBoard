package com.rrtyui.controller;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.service.FinishedMatchesPersistenceService;
import com.rrtyui.util.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class Matches extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = HibernateUtil.getSession();
        var finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance(session);

        String pageParam = req.getParameter("page");
        String filterByNameParam = req.getParameter("filter_by_player_name");

        int page = 1;
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) {
                    page = 1;
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        String filterByName = filterByNameParam != null ? filterByNameParam : "";

        MatchFilter matchFilter = new MatchFilter(page, filterByName);

        var matches = finishedMatchesPersistenceService.getMatchesPagination(matchFilter);

        req.setAttribute("matches", matches);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/matches.jsp");
        dispatcher.forward(req, resp);
    }
}
