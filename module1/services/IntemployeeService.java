package com.mycompany.atmmanagementsys.module1.services;

import java.sql.SQLException;
import java.util.List;

public interface IntemployeeService<employee> {

    void ajouter(employee t) throws SQLException;

    void modifier(employee t) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<employee> get_all() throws SQLException;
    employee get_employee(String identification) throws SQLException;
}