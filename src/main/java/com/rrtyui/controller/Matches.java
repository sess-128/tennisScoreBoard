package com.rrtyui.controller;

import com.rrtyui.dto.MatchFilter;
import com.rrtyui.dto.MatchPageResponseDto;
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
        String filter_by_player_name = req.getParameter("filter_by_player_name");


        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = HibernateUtil.getSession(sessionFactory);

        var finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance(session);

        int pageInt;
        try {
            pageInt = Integer.parseInt(page);
            if (pageInt < 1) {
                pageInt = 1; // Устанавливаем значение по умолчанию, если pageInt меньше 1
            }
        } catch (NumberFormatException e) {
            pageInt = 1; // Устанавливаем значение по умолчанию, если парсинг не удался
        }

        MatchFilter matchFilter = new MatchFilter(pageInt, filter_by_player_name);

        var paginaed = finishedMatchesPersistenceService.pagina(matchFilter);

        req.setAttribute("matches", matches);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/matches.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

//TODO /matches?page=$page_number&filter_by_player_name=$player_name
