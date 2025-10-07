package com.example.tennisscoreboard2025.dao;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class MatchDAO {

    public Optional<Match> findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Match match = session.find(Match.class, id);
        session.close();
        return Optional.ofNullable(match);
    }

    public Optional<Match> findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM Match m WHERE m.player1.name = :name or m.player2.name = :name";
        List <Match> matches = session.createQuery(hql,Match.class).setParameter("name", name).list();
        session.close();
        return matches.stream().findFirst();
    }

    public List<Match> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Match> matchList = session.createQuery("FROM Match", Match.class).list();
        session.close();
        return matchList;
    }

    public void save(Match match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(match);
        tx.commit();
        session.close();
    }
}
