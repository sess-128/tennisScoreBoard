package com.rrtyui.controller;

import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.service.OngoingMatchesService;
import com.rrtyui.util.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/new-match.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = HibernateUtil.getSession(sessionFactory);

        var ongoingMatchesService = OngoingMatchesService.getInstance(session);

        String player1 = req.getParameter("player_1");
        String player2 = req.getParameter("player_2");

        PlayerCreateDto playerCreateDto = new PlayerCreateDto(player1);
        PlayerCreateDto playerCreateDto2 = new PlayerCreateDto(player2);

        var uuid = ongoingMatchesService.createMatch(playerCreateDto, playerCreateDto2);

        resp.sendRedirect("/match-score?UUID=" + uuid);
    }
}
