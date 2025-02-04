package com.rrtyui.util;

import com.rrtyui.entity.Match;
import com.rrtyui.entity.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class)
                .configure();

        return configuration.buildSessionFactory();
    }
}
