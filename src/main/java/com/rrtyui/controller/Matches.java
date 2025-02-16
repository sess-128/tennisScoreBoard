package com.rrtyui.controller;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.entity.Match;
import com.rrtyui.service.FinishedMatchesPersistenceService;
import com.rrtyui.util.HibernateUtil;
import com.rrtyui.util.MatchStorage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class Matches extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String filter_by_player_name = req.getParameter("player_name");

        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = HibernateUtil.getSession(sessionFactory);

        var finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance(session);

//        String uuid = req.getParameter("uuid");
//        if (uuid != null || !uuid.isBlank()) {
//            MatchScoreModel match = MatchStorage.getMatch(uuid);
//            finishedMatchesPersistenceService.saveMatch(match);
//        }

        List<Match> matches = finishedMatchesPersistenceService.getAll();

        req.setAttribute("matches", matches);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/matches");
            dispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

//TODO /matches?page=$page_number&filter_by_player_name=$player_name
