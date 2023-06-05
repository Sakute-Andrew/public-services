package com.ceo.publicservices.infrastructure.dao.impl;

import com.ceo.publicservices.domain.enteties.Service;
import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.infrastructure.dao.ServiceDao;
import com.ceo.publicservices.infrastructure.database.utility.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.ceo.publicservices.infrastructure.dao.impl.MeterReadingDaoImpl.alertWindow;
public class ServiceDaoImpl implements ServiceDao {


    @Override
    public Service findById(int id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try(session){
            session.beginTransaction();
        String hql = "FROM Service WHERE id = :id";
        Query<Service> query = session.createQuery(hql, Service.class);
        query.setParameter("id", id);
        Service service = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        System.out.println(service);
        // Знайдено користувача з вказаним ім'ям
        return service;
        }
        catch (Exception e){
            System.out.println("___________________DAO________________");
            return null;
        }
    }

    @Override
    public List<Service> findAll() {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try (session){
            session.beginTransaction();
            List<Service> query = session.createQuery("from Service", Service.class).getResultList();
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
    public void save(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(service);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Service service) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Service existingService = session.get(Service.class, service.getId());
            existingService.setTariffs(service.getTariffs());
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "DELETE FROM Service WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int rowsAffected = query.executeUpdate(); // Виконання запиту DELETE
            session.getTransaction().commit();
            session.close();
            alertWindow.showAlert(Alert.AlertType.INFORMATION, "Успіх!",
                    "Послугу видалено!");

            System.out.println(rowsAffected + " rows deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
