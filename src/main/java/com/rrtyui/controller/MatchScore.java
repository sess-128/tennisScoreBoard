package com.rrtyui.controller;

import com.rrtyui.dto.MatchScoreModel;
import com.rrtyui.service.MatchScoreCalculationService;
import com.rrtyui.util.MatchStorage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScore extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("UUID");

        req.setAttribute("UUID", uuid);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/match-score.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String playerId = req.getParameter("player");
        String uuid = req.getParameter("UUID");
        MatchScoreModel match = MatchStorage.getMatch(uuid);
        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(match);

        matchScoreCalculationService.addPointToPlayer(playerId);



//        if (!matchScoreCalculationService.isContinue()) {
//            req.setAttribute("uuid", uuid);
//            RequestDispatcher dispatcher = req.getRequestDispatcher("/matches");
//            dispatcher.forward(req, resp);
//        } else {
            resp.sendRedirect(req.getContextPath() + "/match-score?UUID=" + uuid);
//        }
    }
}

