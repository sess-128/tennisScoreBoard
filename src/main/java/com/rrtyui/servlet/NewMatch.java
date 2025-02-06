package com.rrtyui.servlet;

import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.interceptor.TransactionInterceptor;
import com.rrtyui.mapper.PlayerCreateMapper;
import com.rrtyui.mapper.PlayerReadMapper;
import com.rrtyui.repository.PlayerRepository;
import com.rrtyui.service.OngoingMatchesService;
import com.rrtyui.util.HibernateUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@WebServlet("/new-match.html")
public class NewMatch extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));


            var playerRepository = new PlayerRepository(session);
            var playerReadMapper = new PlayerReadMapper();
            var playerCreateMapper = new PlayerCreateMapper();
            var transactionInterceptor = new TransactionInterceptor(sessionFactory);
            OngoingMatchesService ongoingMatchesService = new ByteBuddy()
                    .subclass(OngoingMatchesService.class)
                    .method(ElementMatchers.any())
                    .intercept(MethodDelegation.to(transactionInterceptor))
                    .make()
                    .load(OngoingMatchesService.class.getClassLoader())
                    .getLoaded()
                    .getDeclaredConstructor(PlayerRepository.class, PlayerReadMapper.class, PlayerCreateMapper.class)
                    .newInstance(playerRepository, playerReadMapper, playerCreateMapper);


            String player1 = req.getParameter("player_1");
            String player2 = req.getParameter("player_2");

            PlayerCreateDto playerCreateDto = new PlayerCreateDto(player1);
            PlayerCreateDto playerCreateDto2 = new PlayerCreateDto(player2);

            var uuid = ongoingMatchesService.createMatch(playerCreateDto, playerCreateDto2);

            req.setAttribute("UUID", 2546);
            try {
                resp.sendRedirect("/match-score.jsp?UUID=" + uuid);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
