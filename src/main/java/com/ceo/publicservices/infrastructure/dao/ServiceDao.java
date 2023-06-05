package com.ceo.publicservices.infrastructure.dao;

import com.ceo.publicservices.domain.enteties.Service;


import java.util.List;

public interface ServiceDao {
    Service findById(int id);
    List<Service> findAll();
    void save(Service service);
    void update(Service service);
    void delete(int id);
}


