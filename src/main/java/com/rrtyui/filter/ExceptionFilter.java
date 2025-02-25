package com.rrtyui.filter;

import com.rrtyui.exception.IncorrectNameException;
import com.rrtyui.exception.IncorrectPageNumberException;
import com.rrtyui.exception.IncorrectUUIDException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch (IncorrectNameException exception) {
            req.setAttribute("errorMessage", exception.getMessage());
            req.getRequestDispatcher("/new-match.jsp").forward(req, res);
        } catch (IncorrectUUIDException exception) {
            req.setAttribute("errorMessage", exception.getMessage());
            req.getRequestDispatcher("/match-score.jsp").forward(req, res);
        } catch (IncorrectPageNumberException exception) {
            req.setAttribute("errorMessage", exception.getMessage());
            req.getRequestDispatcher("/matches.jsp").forward(req, res);
        }
    }
}
