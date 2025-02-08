package com.rrtyui.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("UUID");

        req.setAttribute("UUID", uuid);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/match-score.jsp");
        dispatcher.forward(req, resp);
    }
}
/**
 * У нас есть мапа текущих матчей <UUID, MatchScoreModel>
 */
