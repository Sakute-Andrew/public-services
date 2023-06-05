package com.ceo.publicservices.infrastructure.dao;

import com.ceo.publicservices.domain.enteties.MeterReading;


import java.util.List;

public interface MeterReadingDao {
    List<MeterReading> findByUsername(int id);
    List<MeterReading> findAll();
    void save(MeterReading meterReading);
    void delete(int id);
}
