package com.mycompany.atmmanagementsys.module1.services;

import com.mycompany.atmmanagementsys.module1.models.employee;
import com.mycompany.atmmanagementsys.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class employeeService implements IntemployeeService<employee>{
    private Connection connection;

    public employeeService() {
        connection = DbConnection.getInstance().getConnection();
    }
    @Override
    public void ajouter(employee t) throws SQLException {

    }

    @Override
    public void modifier(employee t) throws SQLException {

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<employee> get_all() throws SQLException {
        return null;
    }

    @Override
    public employee get_employee(String identification) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from client where identification_number=?");
            ps.setString(1, identification);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {


                employee e = new employee();
                e.setId(rs.getInt("id"));
                e.setIdentification_number(rs.getString("identification_number"));
                e.setPassword(rs.getString("password"));
                e.setName(rs.getString("name"));
                e.setLast_name(rs.getString("last_name"));
                e.setAddress(rs.getString("address"));
                e.setSalaire(rs.getDouble("salaire"));
                e.setCin(rs.getString("cin"));
                e.setDate_ajout(rs.getString("date_ajout"));
                e.setAdded_by(rs.getInt("added_by"));
                e.setMail(rs.getString("mail"));
//                if()
                System.out.println("200:client trouvé");
                return e;

            } else {
                System.out.println("404:client non trouvé");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("404:" + e.getMessage());
            return null;
        }
    }

}
