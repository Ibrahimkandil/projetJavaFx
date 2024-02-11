package com.mycompany.atmmanagementsys.module1.services;

import java.sql.SQLException;
import java.util.List;

public interface IntClientService<client> {

    String ajouter(client t) throws SQLException;

    void modifier_compte(client t) throws SQLException;
    String modifier_password(String identification,String newPass) throws SQLException;

    String supprimer(int id) throws SQLException;

    List<client> get_all() throws SQLException;
    client get_client(String identification) throws SQLException;
}