package com.ceo.publicservices.infrastructure.dao;

import com.ceo.publicservices.domain.enteties.User;

import java.util.List;

public interface UserDao {
    User findByName(String user);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(int id);
}