package com.example.tennisscoreboard2025.dao;

import com.example.tennisscoreboard2025.models.Match;
import com.example.tennisscoreboard2025.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchDAO {

    public Optional<Match> findById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Match match = session.find(Match.class, id);
        //session.close();
        return Optional.ofNullable(match);
    }

    public int countByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("SELECT COUNT(m) FROM Match m " +
                "WHERE m.player1.name LIKE :name OR m.player2.name LIKE :name");
        query.setParameter("name","%" + name + "%");
        //session.close();
        return query.uniqueResult().intValue();
    }

    public List<Match> findByName(String name, int page, int pageSize){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Match> query = session.createQuery("FROM Match m WHERE m.player1.name " +
                "LIKE :name OR m.player2.name LIKE :name " +
                "ORDER BY m.id ASC", Match.class);
        query.setParameter("name","%" + name + "%");
        query.setFirstResult((page-1)*pageSize);
        query.setMaxResults(pageSize);
        //session.close();
        return query.getResultList();
    }

    public Optional<Match> findByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String hql = "FROM Match m WHERE m.player1.name = :name or m.player2.name = :name";
        List <Match> matches = session.createQuery(hql,Match.class).setParameter("name", name).list();
        //session.close();
        return matches.stream().findFirst();
    }

    public int count(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Long> query = session.createQuery("select count(m) from Match m", Long.class);
        //session.close();
        return query.uniqueResult().intValue();
    }

    public List<Match> findAll(int page, int pageSize){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Match> query = session.createQuery("FROM Match m ORDER BY m.id DESC", Match.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        //session.close();
        return query.list();
    }
    public List<Match> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Match> matchList = session.createQuery("FROM Match", Match.class).list();
        //session.close();
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
