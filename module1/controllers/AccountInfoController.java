package com.mycompany.atmmanagementsys.module1.controllers;

import com.mycompany.atmmanagementsys.utils.Quotes;
import com.mycompany.atmmanagementsys.utils.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountInfoController implements Initializable {
    
    String UserID;
    String uiD;
    @FXML
    private Label ResultMSG;
    @FXML
    private TextField uid;
    @FXML
    private TextField uname;
    @FXML
    private TextArea uaddress;
    @FXML
    private TextField uemail;
    @FXML
    private TextField uphone;
    @FXML
    private TextField ubalance;
    @FXML
    private Label welcome;
    @FXML
    private PasswordField oldpass;
    @FXML
    private PasswordField newpass;
    @FXML
    private PasswordField passretype;
    @FXML
    private Label changepassconf;
    @FXML
    private TextArea quotediscp;
    @FXML
    private TextArea quotedisai;

    public void getUserID(String Id) {
        UserID = Id;
    }

    public void StqPasswordChangePage() {
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotediscp.setText(quote);
    }

    public void StqAccountInfo() {
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotedisai.setText(quote);
    }

    public void ChangeInfo(ActionEvent event) {
        try {
            Connection con = DbConnection.Connection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE identification_number = ?");
            ps.setString(1, UserID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");

                if (!uname.getText().isEmpty()) {
                    name = uname.getText();
                }
                if (!uaddress.getText().isEmpty()) {
                    address = uaddress.getText();
                }
                if (!uemail.getText().isEmpty()) {
                    email = uemail.getText();
                }
                if (!uphone.getText().isEmpty()) {
                    phone = uphone.getText();
                }

                PreparedStatement ps1 = con.prepareStatement("UPDATE users SET name = ?, address = ?, email = ?, phone = ? WHERE identification_number = ?");
                ps1.setString(1, name);
                ps1.setString(2, address);
                ps1.setString(3, email);
                ps1.setString(4, phone);
                ps1.setString(5, UserID);
                ps1.executeUpdate(); // Corrected line

                uname.setText(name);
                uaddress.setText(address);
                uemail.setText(email);
                uphone.setText(phone);
                ResultMSG.setText("Information Updated");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setiud(String Bal) {
        uiD = Bal;
        uid.setText(uiD);
    }
    public void setuname(String Bal) {

        uname.setText(Bal);
    };
    public void setuaddress(String Bal) {
        uaddress.setText(Bal);
    }
    public void setuemail(String Bal) {

        uemail.setText(Bal);
    }
    public void setuphone(String Bal) {

        uphone.setText(Bal);
    }
   public void ChangePassword(ActionEvent event) throws SQLException{
       if(!newpass.getText().equals(passretype.getText())){
       changepassconf.setText("Password Confirmation Failed");
       passretype.setText("");
       passretype.setStyle("-fx-border-color:red;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");
       }
       else if(oldpass.getText().isEmpty()||newpass.getText().isEmpty()||passretype.getText().isEmpty()){
       changepassconf.setText("Please Fill Up Everything Correctly.");
       }
       else{
       Connection con = DbConnection.Connection();
       PreparedStatement ps = con.prepareStatement("UPDATE users SET password = ? WHERE id ='"+UserID+"' AND password ='"+oldpass.getText()+"'");
       ps.setString(1, newpass.getText());
       int i = ps.executeUpdate();
       if(i>0){
       changepassconf.setText("Password Changed.");
       }
       else{
       changepassconf.setText("Wrong Password.");
       }
       oldpass.setText("");
       newpass.setText("");
       passretype.setText("");
       passretype.setStyle("-fx-border-color: #3b5998;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");
       ps.close();
       con.close();
       }
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
