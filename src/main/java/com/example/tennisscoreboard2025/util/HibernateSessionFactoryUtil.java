package com.example.tennisscoreboard2025.util;
import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Player;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            cfg.addAnnotatedClass(Player.class);
            cfg.addAnnotatedClass(Match.class);

            sessionFactory = cfg.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                            .applySettings(cfg.getProperties())
                            .build()
            );
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Failed to init SessionFactory: " + e);
        }
    }

    private HibernateSessionFactoryUtil() {}

}
