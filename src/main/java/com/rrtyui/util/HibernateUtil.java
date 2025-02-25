package com.rrtyui.util;

import com.rrtyui.entity.Match;
import com.rrtyui.entity.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Proxy;

@UtilityClass
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class)
                .setProperty("hibernate.current_session_context_class", "thread")
                .configure();

        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args1) -> method.invoke(sessionFactory.getCurrentSession(), args1));
    }

}
