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
        Transaction tx = session.beginTransaction();
        Optional<Player> player = Optional.of(session.find(Player.class, id));
        session.close();
        return player;
    }

    public Optional<Player> findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String hql = "FROM Player WHERE name = :name";
        List<Player> playersList = session.createQuery(hql).setParameter("name", name).getResultList();
        int countPlayers =  playersList.size();
        if (countPlayers != 0) {
            return  Optional.of(playersList.get(0));
        } else {
            return Optional.empty();
        }
    }

    public List<Player> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return Collections.emptyList(); // dodelat'
    }

    public void save(Player p) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(p);
        tx.commit();
        session.close();
    }
}
