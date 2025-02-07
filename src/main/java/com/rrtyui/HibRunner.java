package com.rrtyui;

import com.rrtyui.dto.PlayerCreateDto;
import com.rrtyui.entity.Player;
import com.rrtyui.interceptor.TransactionInterceptor;
import com.rrtyui.mapper.PlayerCreateMapper;
import com.rrtyui.mapper.PlayerReadMapper;
import com.rrtyui.repository.PlayerRepository;
import com.rrtyui.service.OngoingMatchesService;
import com.rrtyui.util.HibernateUtil;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class HibRunner {


//    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//
//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
//                    (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
//
//
//
////            session.beginTransaction();
//
//
//
//            var playerRepository = new PlayerRepository(session);
//            var playerReadMapper = new PlayerReadMapper();
//            var playerCreateMapper = new PlayerCreateMapper();
//            var transactionInterceptor = new TransactionInterceptor(sessionFactory);
//            OngoingMatchesService ongoingMatchesService = new ByteBuddy()
//                    .subclass(OngoingMatchesService.class)
//                    .method(ElementMatchers.any())
//                    .intercept(MethodDelegation.to(transactionInterceptor))
//                    .make()
//                    .load(OngoingMatchesService.class.getClassLoader())
//                    .getLoaded()
//                    .getDeclaredConstructor(PlayerRepository.class, PlayerReadMapper.class, PlayerCreateMapper.class)
//                    .newInstance(playerRepository, playerReadMapper, playerCreateMapper);
//
//
////
////            Player player = Player.builder()
////                    .name("Капитан залупа")
////                    .build();
////            playerRepository.save(player);
//
////            var ongoingMatchesService = new OngoingMatchesService(playerRepository, playerReadMapper, playerCreateMapper);
//
////            ongoingMatchesService.findById(1L).ifPresent(System.out::println);
//
//            PlayerCreateDto playerCreateDto = new PlayerCreateDto("dima");
//            ongoingMatchesService.create(playerCreateDto);
//
////            session.getTransaction().commit();
//
//        }
//    }
}
