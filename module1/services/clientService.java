package com.mycompany.atmmanagementsys.module1.services;

import com.mycompany.atmmanagementsys.module1.models.client;
import com.mycompany.atmmanagementsys.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class clientService implements IntClientService<client> {

    private Connection connection;

    public clientService() {
        connection = DbConnection.getInstance().getConnection();
    }

    private String identification_number;
    private String password;
    private String name;
    private String last_name;
    private double balance;
    private String address;
    private String email;
    private String phone;

    @Override
    public String ajouter(client t) throws SQLException {
        try {

            PreparedStatement ps = connection.prepareStatement("insert into users (identification_number,password, name,last_name, adress, phone, email, balance) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, t.getIdentification_number());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getName());
            ps.setString(4, t.getLast_name());
            ps.setString(5, t.getAddress());
            ps.setString(6, t.getPhone());
            ps.setString(7, t.getEmail());
            ps.setDouble(8, t.getBalance());
            ps.executeUpdate();
            System.out.println("201:client ajouté");
        } catch (SQLException e) {
            System.out.println("404:" + e.getMessage());
        }
        return t.toString_Id();


    }

    @Override
    public void modifier_compte(client t) throws SQLException {

        client c = get_client(t.getIdentification_number());

        if (c.getName() != t.getName()) {
            t.setName(c.getName());
        }
        if (c.getLast_name() != t.getLast_name()) {
            t.setLast_name(c.getLast_name());
        }
        if (c.getAddress() != t.getAddress()) {
            t.setAddress(c.getAddress());
        }
        if (c.getPhone() != t.getPhone()) {
            t.setPhone(c.getPhone());
        }
        if (c.getEmail() != t.getEmail()) {
            t.setEmail(c.getEmail());
        }
        try {
            PreparedStatement ps = connection.prepareStatement("update users set name=?, last_name=?, adress=?, phone=?, email=? where identification_number=?");
            ps.setString(1, t.getName());
            ps.setString(2, t.getLast_name());
            ps.setString(3, t.getAddress());
            ps.setString(4, t.getPhone());
            ps.setString(5, t.getEmail());
            ps.setString(6, c.getIdentification_number());
            ps.executeUpdate();
            System.out.println("202:compte modifié");
        } catch (SQLException e) {
            System.out.println("404:" + e.getMessage());
        }

    }

    @Override
    public String modifier_password(String identification, String newPass) throws SQLException {
        client c = get_client(identification);
        if (c.getPassword() != newPass) {
            c.setPassword(newPass);
        }
        try {
            PreparedStatement ps = connection.prepareStatement("update users set password=? where identification_number=?");
            ps.setString(1, c.getPassword());
            ps.setString(2, c.getIdentification_number());
            ps.executeUpdate();
            System.out.println("202:Password Has been Changed");
            return "Password Has been Changed" + '\n' + "PLEASE KEEP IT SAFE";

        } catch (SQLException e) {
            System.out.println("404:" + e.getMessage());
            return null;

        }

    }

    @Override
    public String supprimer(int id) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("203:deleted");
            return "client supprimé";
        } catch (SQLException e) {
            System.out.println("404:" + e.getMessage());
            return null;
        }

    }

    @Override
    public List<client> get_all() throws SQLException {
        List<client> clients = null;
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                client c = new client();
                c.setId(rs.getInt("id"));
                c.setIdentification_number(rs.getString("identification_number"));
                c.setPassword(rs.getString("password"));
                c.setName(rs.getString("name"));
                c.setLast_name(rs.getString("last_name"));
                c.setAddress(rs.getString("adress"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setBalance(rs.getDouble("balance"));
                clients.add(c);

            }
            if (clients != null) {
                System.out.println("200:clients trouvés");
                return clients;
            } else {
                System.out.println("404:clients non trouvés");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("404:" + e.getMessage());
            return null;
        }
    }

    @Override
    public client get_client(String identification) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where identification_number=?");
            ps.setString(1, identification);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                client c = new client();
                c.setId(rs.getInt("id"));
                c.setIdentification_number(rs.getString("identification_number"));
                c.setPassword(rs.getString("password"));
                c.setName(rs.getString("name"));
                c.setLast_name(rs.getString("last_name"));
                c.setAddress(rs.getString("adress"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setBalance(rs.getDouble("balance"));
                System.out.println("200:client trouvé");
                return c;

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