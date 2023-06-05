package com.ceo.publicservices.infrastructure.dao.impl;

import com.ceo.publicservices.domain.enteties.MeterReading;
import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.MeterReadingDao;
import com.ceo.publicservices.infrastructure.database.utility.HibernateUtil;
import com.ceo.publicservices.presentation.controller.AlertWindow;
import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MeterReadingDaoImpl implements MeterReadingDao {


    static AlertWindow alertWindow = new AlertWindow();
    @Override
    public List<MeterReading> findByUsername(int id) {
      try (Session session = HibernateUtil.getSessionFactory().openSession()){
          session.beginTransaction();

          String hql = "FROM MeterReading WHERE user_id = :id";
          Query query = session.createQuery(hql, MeterReading.class);
          query.setParameter("id", id);
          List<MeterReading> userList = query.getResultList();
          session.getTransaction().commit();
          session.close();
          System.out.println("-----------------------------------------------------------------------------------------");
          return userList;
      }

    }

    @Override
    public List<MeterReading> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            String hql = "SELECT * FROM meter_readings";
            List<MeterReading> query = session.createNativeQuery(hql, MeterReading.class).getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("-----------------------------------------------------------------------------------------");
            return query;


        } catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public void save(MeterReading meterReading) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(meterReading);
            session.getTransaction().commit();
            session.close();
            System.out.println("-----------------------------------------------------------------------------------------");
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "DELETE FROM MeterReading WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int rowsAffected = query.executeUpdate(); // Виконання запиту DELETE
            session.getTransaction().commit();
            session.close();

            System.out.println(rowsAffected + " rows deleted.");

            alertWindow.showAlert(Alert.AlertType.INFORMATION, "Успіх!",
                    "Запис видалено!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
