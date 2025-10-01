package com.example.tennisscoreboard2025.dao;

import com.example.tennisscoreboard2025.models.Player;
import com.example.tennisscoreboard2025.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerDAO {
    public void save(Player p) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        }
    }
}
