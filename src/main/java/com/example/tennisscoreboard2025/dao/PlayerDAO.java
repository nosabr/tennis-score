package com.example.tennisscoreboard2025.dao;

import com.example.tennisscoreboard2025.models.Player;
import com.example.tennisscoreboard2025.util.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlayerDAO {

    public Optional<Player> findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Player player = session.find(Player.class, id);
        session.close();
        return Optional.ofNullable(player);
    }

    public Optional<Player> findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM Player WHERE name = :name";
        List<Player> playersList = session
                .createQuery(hql, Player.class)
                .setParameter("name", name)
                .getResultList();
        session.close();
        return playersList.stream().findFirst();
    }

    public void save(Player p) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }
}
