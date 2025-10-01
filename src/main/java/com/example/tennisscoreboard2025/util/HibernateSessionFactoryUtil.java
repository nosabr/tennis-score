package com.example.tennisscoreboard2025.util;
import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
    public static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil(){};

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().
                        configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Player.class)
                        .addAnnotatedClass(Match.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Exception caught in HibernateSessionFactoryUtil" + e.getMessage());
            }
        }
        return sessionFactory;
    }
}
