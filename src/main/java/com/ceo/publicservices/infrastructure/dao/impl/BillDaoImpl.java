package com.ceo.publicservices.infrastructure.dao.impl;

import com.ceo.publicservices.domain.enteties.Bill;
import com.ceo.publicservices.infrastructure.dao.BillDao;
import com.ceo.publicservices.infrastructure.database.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BillDaoImpl implements BillDao {

    public List<Bill> findAll(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            String hql = "FROM Bill WHERE user_id = :id";
            Query query = session.createQuery(hql, Bill.class);
            query.setParameter("id", id);
            List<Bill> billList = query.getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("-----------------------------------------------------------------------------------------");
            return billList;
        }

    }

    @Override
    public void save(Bill bill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(bill);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
