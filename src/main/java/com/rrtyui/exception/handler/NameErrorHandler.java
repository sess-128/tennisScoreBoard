package com.rrtyui.exception.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NameErrorHandler implements ErrorHandler{
    @Override
    public void handle(Exception e, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("errorMessage", e.getMessage());
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }
}
