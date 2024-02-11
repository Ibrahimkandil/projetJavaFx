package com.mycompany.atmmanagementsys.module1.services;

import java.sql.SQLException;

public interface IntadminService<admin> {

    void ajouter(admin t) throws SQLException;

    void modifier(admin t) throws SQLException;

    void supprimer(int id) throws SQLException;


    admin get_client(int identification) throws SQLException;
}