package com.rrtyui.exception.handler;

import com.rrtyui.exception.IncorrectNameException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorHandlerRegistry {
    private static final Map<Class<? extends Exception>, ErrorHandler> handlers = new HashMap<>();

    static {
        handlers.put(IncorrectNameException.class, new NameErrorHandler());
    }

    public static ErrorHandler getHandler(Class<? extends Exception> exceptionClass) {
        return handlers.getOrDefault(exceptionClass, new DefaultErrorHandler());
    }

    public static class DefaultErrorHandler implements ErrorHandler {

        @Override
        public void handle(Exception e, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
        }
    }
}