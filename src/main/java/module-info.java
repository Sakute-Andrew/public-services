module com.ceo.publicservices{
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.naming;
    requires java.persistence;
    requires java.sql;
    requires javafx.fxml;

    requires org.hibernate.orm.core;
    requires lombok;
    requires com.google.common;


    opens com.ceo.publicservices to javafx.fxml, javafx.graphics;
    opens com.ceo.publicservices.domain.enteties to org.hibernate.orm.core;
    opens com.ceo.publicservices.presentation.controller.mainmenu to javafx.fxml;
    opens com.ceo.publicservices.presentation.controller.adminpanel to javafx.fxml;
    opens com.ceo.publicservices.presentation.controller.authentification to javafx.fxml;
    exports com.ceo.publicservices;
    exports com.ceo.publicservices.presentation.controller.mainmenu;
    exports com.ceo.publicservices.presentation.controller.adminpanel;
    exports com.ceo.publicservices.presentation.controller.authentification;
    exports com.ceo.publicservices.domain.enteties;
    exports com.ceo.publicservices.domain.validation;
    exports com.ceo.publicservices.infrastructure.dao.impl;
    exports com.ceo.publicservices.infrastructure.dao;
    exports com.ceo.publicservices.infrastructure.database.utility;
}