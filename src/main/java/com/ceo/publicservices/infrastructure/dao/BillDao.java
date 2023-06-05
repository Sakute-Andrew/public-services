package com.ceo.publicservices.infrastructure.dao;

import com.ceo.publicservices.domain.enteties.Bill;

import java.util.List;

public interface BillDao {

    List<Bill> findAll(int id);
    void save(Bill bill);
}
