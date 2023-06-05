package com.ceo.publicservices.infrastructure.dao;

import com.ceo.publicservices.domain.enteties.Admin;

public interface AdminDao {
    Admin findByName(String user);
}
