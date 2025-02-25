    package com.rrtyui.controller;

    import com.rrtyui.dto.MatchScoreModel;
    import com.rrtyui.exception.IncorrectUUIDException;
    import com.rrtyui.service.MatchScoreCalculationService;
    import com.rrtyui.util.MatchStorage;
    import com.rrtyui.util.Validation;
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


            MatchScoreModel match = MatchStorage.getMatch(uuid);
            Validation.validateUUID(uuid, match);

            req.setAttribute("match", match);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/match-score.jsp");
            dispatcher.forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String playerId = req.getParameter("player");
            String uuid = req.getParameter("UUID");

            MatchScoreModel match = MatchStorage.getMatch(uuid);
            MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(match);

            matchScoreCalculationService.addPointToPlayer(playerId);

            resp.sendRedirect(req.getContextPath() + "/match-score?UUID=" + uuid);
        }
    }

