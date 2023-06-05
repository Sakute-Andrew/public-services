package com.ceo.publicservices.infrastructure.dao.impl;

import com.ceo.publicservices.domain.enteties.User;
import com.ceo.publicservices.domain.enteties.UserSingleton;
import com.ceo.publicservices.infrastructure.dao.UserDao;
import com.ceo.publicservices.infrastructure.database.utility.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

import static com.ceo.publicservices.infrastructure.dao.impl.MeterReadingDaoImpl.alertWindow;

public class UserDaoImpl implements UserDao {

    final Session session = HibernateUtil.getSessionFactory().openSession();
    @Override
    public User findByName(String username) {
        session.beginTransaction();
        String hql = "FROM User WHERE username = :name";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("name", username);
        User user = (User) query.uniqueResult();
        session.getTransaction().commit();
        // Знайдено користувача з вказаним ім'ям
        return user;
    }

    @Override
    public List<User> findAll() {
       try (session){
           session.beginTransaction();
           String hql = "SELECT * FROM Users";
           List<User> query = session.createNativeQuery(hql, com.ceo.publicservices.domain.enteties.User.class).getResultList();

           session.getTransaction().commit();
           System.out.println("-----------------------------------------------------------------------------------------");
           return query;


       } catch (Exception e){
           e.printStackTrace();
           return null;

       }
    }

    @Override
    public void save(User user) {
        try (session){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();


        }

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "DELETE FROM User WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int rowsAffected = query.executeUpdate(); // Виконання запиту DELETE
            session.getTransaction().commit();
            session.close();
            alertWindow.showAlert(Alert.AlertType.INFORMATION, "Успіх!",
                    "Користувача видалено!");

            System.out.println(rowsAffected + " rows deleted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

