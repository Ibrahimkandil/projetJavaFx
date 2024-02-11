package com.mycompany.atmmanagementsys.module2.controllers;

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

public class BalancePageController implements Initializable {
    @FXML
    private TextField balanceinfo;
    @FXML
    private TextArea quotedisbp;
    @FXML
    private TextField withdrawamount;
    @FXML
    private TextField depositamount;
    String UserID;
    String BalanceS;
    @FXML
    private TextField receiverid;
    @FXML
    private TextField trnasamount;
    @FXML
    private PasswordField retypepass;
    @FXML
    private Label dipositconf;
    @FXML
    private Label withdrawinfo;
    @FXML
    private TextArea quotedisdp;
    @FXML
    private TextArea quotediswp;
    @FXML
    private Label transferconf;
    @FXML
    private TextArea quotedisbt;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        // TODO
    }
    public void getUserID(String Id) {
        UserID = Id;
    }
    public void SetBalance(String Bal) {
        BalanceS = Bal;
        balanceinfo.setText(BalanceS);
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotedisbp.setText(quote);
    }
    public void StqDepositPage() {
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotedisdp.setText(quote);
    }
    public void StqWithdrawPage() {
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotediswp.setText(quote);
    }
    public void StqBalanceTransPage() {
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotedisbt.setText(quote);
    }
    @FXML
    public void WithdrawMoney(ActionEvent event) {
        int idUser;
        try {
            if (withdrawamount.getText().isEmpty() || Double.parseDouble(withdrawamount.getText()) < 0) {
                withdrawinfo.setText("Please Enter A Valid Amount");
            } else {
                Connection con = DbConnection.Connection();
                PreparedStatement ps = null;
                ResultSet rs = null;
                double balance;

                try {
                    con.setAutoCommit(false); // Disable auto-commit for transaction

                    // Select user's current balance
                    ps = con.prepareStatement("SELECT * FROM users WHERE identification_number = ?");
                    ps.setString(1, UserID);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        idUser=rs.getInt("id");
                        double currentBalance = rs.getDouble("balance");
                        double withdrawalAmount = Double.parseDouble(withdrawamount.getText());
                        double newBalance = currentBalance - withdrawalAmount;

                        if (newBalance < 0) {
                            withdrawinfo.setText("Your Account Balance Is Low");
                        } else {
                            // Update user's balance
                            ps = con.prepareStatement("UPDATE users SET balance = ? WHERE identification_number = ?");
                            ps.setDouble(1, newBalance);
                            ps.setString(2, UserID);
                            ps.executeUpdate();
                            System.out.println("Balance updated");

                            // Insert a row into Historique_transfert
                            ps = con.prepareStatement("INSERT INTO Historique_transfert (user_id, Type_transfert, Montant) VALUES (?, ?, ?)");
                            ps.setInt(1, idUser);
                            ps.setString(2, "withdraw");
                            ps.setDouble(3, withdrawalAmount*-1);
                            ps.executeUpdate();
                            System.out.println("Historique_transfert updated");

                            withdrawinfo.setText("Transaction Successful");
                        }
                    }

                    con.commit(); // Commit the transaction
                } catch (SQLException e) {
                    con.rollback(); // Rollback the transaction in case of an exception
                    withdrawinfo.setText("Transaction Failed");
                    e.printStackTrace();
                } finally {
                    con.setAutoCommit(true); // Re-enable auto-commit
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                    con.close();
                }
            }
        } catch ( NumberFormatException e) {
            dipositconf.setText("Invalid Database Or Number Format1");
        }
        catch (SQLException e){
            dipositconf.setText("Invalid Database Or Number Format22");
            System.out.println(e);

        }


        withdrawamount.setText("");
    }

    @FXML
    public void Deposit(ActionEvent event) {
        try {
            if (depositamount.getText().isEmpty() || Double.parseDouble(depositamount.getText()) < 0) {
                dipositconf.setText("Please Enter A Valid Amount");
                return;
            }

            double depositAmount = Double.parseDouble(depositamount.getText());
            Connection con = DbConnection.Connection();
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                // Select user information by identification number
                ps = con.prepareStatement("SELECT id, balance FROM users WHERE identification_number = ?");
                ps.setString(1, UserID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    int idUser = rs.getInt("id");
                    double currentBalance = rs.getDouble("balance");
                    double newBalance = currentBalance + depositAmount;

                    // Update user's balance
                    ps = con.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
                    ps.setDouble(1, newBalance);
                    ps.setInt(2, idUser);
                    ps.executeUpdate();

                    // Insert a row into Historique_transfert with positive amount for deposit
                    ps = con.prepareStatement("INSERT INTO Historique_transfert (user_id, Type_transfert, Montant) VALUES (?, ?, ?)");
                    ps.setInt(1, idUser);
                    ps.setString(2, "deposit"); // Assuming 1 represents a deposit, adjust based on your Type_transfert values
                    ps.setDouble(3, depositAmount);
                    ps.executeUpdate();

                    // Update the total balance in the compte_stat table
                    ps = con.prepareStatement("UPDATE comptes_stat SET totale = totale + ? WHERE id_user = ?");
                    ps.setInt(1, (int) depositAmount);
                    ps.setInt(2, idUser);
                    ps.executeUpdate();

                    dipositconf.setText("Cash Has Been Deposited");
                } else {
                    dipositconf.setText("User not found");
                }
            } catch (SQLException e) {
                dipositconf.setText("Transaction Failed: " + e.getMessage());
                System.out.println(e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                con.close();
            }
        } catch (NumberFormatException | SQLException e) {
            dipositconf.setText("Invalid Database Or Number Format: " + e.getMessage());
        }

        depositamount.clear();
    }

    @FXML
    public void TransferMoney(ActionEvent event) throws SQLException {
        try {
            if (trnasamount.getText().isEmpty() || Integer.parseInt(trnasamount.getText()) < 0 || retypepass.getText().isEmpty() || receiverid.getText().isEmpty()) {
                transferconf.setText("Please Fill Up Everything Correctly.");
            }
            else {
                int senderId = 0;
                int receiverId = 0;
                Connection con = DbConnection.Connection();
                //Modication dans le ligne sender
                PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE identification_number = ? and password = ? ");
                receiverid.setStyle("-fx-border-color: #e65100;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");
                ps.setString(1, UserID);
                ps.setString(2, retypepass.getText());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    retypepass.setStyle("-fx-border-color: #e65100;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");
                    double newbalance = rs.getDouble("balance") - Double.parseDouble(trnasamount.getText());
                    senderId = rs.getInt("id");
                    if (newbalance < 0) {
                        transferconf.setText("You Dont Have Enough Money To Transfer.");
                        trnasamount.setText("");


                    } else {
                        System.out.println("Sender ID: " + senderId);
                        PreparedStatement ps1 = con.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
                        ps1.setDouble(1, newbalance);
                        ps1.setInt(2, senderId);
                        ps1.executeUpdate();

                    }

                } else {
                    transferconf.setText("UserID Invalid , Failed To Transfer.");
                    receiverid.setText("");
                    receiverid.setStyle("-fx-border-color:red;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");

                }
                //Modication dans le ligne receiver
                PreparedStatement ps2 = con.prepareStatement("SELECT * FROM users WHERE identification_number =?");
                receiverid.setStyle("-fx-border-color: #e65100;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");
                ps2.setString(1, receiverid.getText());
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    receiverId = rs2.getInt("id");
                    double newAmount = rs2.getDouble("balance") + Double.parseDouble(trnasamount.getText());

                    PreparedStatement ps3 = con.prepareStatement("UPDATE users SET balance = ? WHERE id = ?");
                    ps3.setDouble(1, newAmount);
                    ps3.setInt(2, receiverId);
                    ps3.executeUpdate();


                } else {
                    transferconf.setText("UNKNOWN Receiver,Failed to transfer");
                    receiverid.setText("");
                    receiverid.setStyle("-fx-border-color:red;-fx-border-width:2;-fx-border-radius:20;-fx-background-radius:22;");
                }
                //creation d'un nouveau ligne du historique
                PreparedStatement ps3 = con.prepareStatement("INSERT INTO Historique_transfert (user_id, Type_transfert, Montant,destination) VALUES (?, ?, ?,?)");
                ps3.setInt(1, senderId);
                ps3.setString(2, "transfer");
                ps3.setDouble(3, Double.parseDouble(trnasamount.getText()));
                ps3.setInt(4, receiverId);
                ps3.executeUpdate();

                //update the total balace in the comptes_stat table
                PreparedStatement ps4 = con.prepareStatement("UPDATE comptes_stat SET totale = totale + ? WHERE id_user = ?");
                ps4.setDouble(1, Integer.parseInt(trnasamount.getText()));
                ps4.setInt(2, receiverId);
                ps4.executeUpdate();


                transferconf.setText("Transfer Successfull");
                receiverid.setText("");
                trnasamount.setText("");
                retypepass.setText("");


                con.close();

            }
        }catch (NumberFormatException | SQLException e) {
            transferconf.setText("Invalid Database Or Number Format");
            System.out.println( e.getMessage());
        }


    }
}
