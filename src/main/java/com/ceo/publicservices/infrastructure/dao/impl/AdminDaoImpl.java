package com.ceo.publicservices.infrastructure.dao.impl;

import com.ceo.publicservices.domain.enteties.Admin;
import com.ceo.publicservices.infrastructure.dao.AdminDao;
import com.ceo.publicservices.infrastructure.database.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AdminDaoImpl implements AdminDao {

    final Session session = HibernateUtil.getSessionFactory().openSession();
    @Override
    public Admin findByName(String user) {
        session.beginTransaction();
        String hql = "FROM Admin WHERE username = :name";
        Query<Admin> query = session.createQuery(hql, Admin.class);
        query.setParameter("name", user);
        Admin admin = (Admin) query.uniqueResult();
        session.getTransaction().commit();
        // Знайдено користувача з вказаним ім'ям
        return admin;
    }
}
