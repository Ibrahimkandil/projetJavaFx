package com.mycompany.atmmanagementsys.module1.controllers;

import com.mycompany.atmmanagementsys.module2.controllers.BalancePageController;
import com.mycompany.atmmanagementsys.utils.DbConnection;
import com.mycompany.atmmanagementsys.utils.Quotes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {
    @FXML
    private Label welcome;
    @FXML
    private TextArea quotedis;
    @FXML
            private PieChart pieChart;
    String UserID, UserName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void SetpieChart( double x1,double x2){
            PieChart.Data slice1 = new PieChart.Data("Balance", x1);
            PieChart.Data slice2 = new PieChart.Data("Dépense", x2);
            pieChart.getData().addAll(slice1,slice2);

            pieChart.setLegendVisible(false);
            pieChart.setLabelsVisible(true);
            pieChart.setTitle("Pie Chart");

        }
    public void GetUserID(String id, String Name) throws SQLException {
        welcome.setText(Name);
        UserID = id;
        UserName = Name;
        Quotes qt = new Quotes();
        String quote = qt.returnQuotes();
        quotedis.setText(quote);

    }
    public void CheckBalance(ActionEvent event) throws SQLException, IOException {
        Connection con = DbConnection.Connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement("SELECT * FROM users WHERE identification_number = ? AND name = ?");
        ps.setString(1, UserID);
        ps.setString(2, UserName);
        rs = ps.executeQuery();
        while (rs.next()) {
            String Balance = Integer.toString(rs.getInt("balance"));
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/BalancePage.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            BalancePageController bpc = loader.getController();
            bpc.SetBalance(Balance);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/BalancePage.css");
            Image icon = new Image("/icons/DepositPage.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Balance Page");
            stage.setScene(scene);
            stage.show();
        }
        ps.close();
        rs.close();
    }
    @FXML
    public void Withdraw(ActionEvent event) throws IOException, SQLException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/WithdrawPage.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        BalancePageController bpc = loader.getController();
        bpc.getUserID(UserID);
        bpc.StqWithdrawPage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/WithdrawPage.css");
        Image icon = new Image("/icons/DepositPage.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Withdraw Page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void DepositMoney(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/DepositPage.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        BalancePageController bpc = loader.getController();
        bpc.getUserID(UserID);
        bpc.StqDepositPage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/DepositPage.css");
        Image icon = new Image("/icons/DepositPage.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Deposite Page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void AccountInfo(ActionEvent event) throws SQLException, IOException {
        Connection con = DbConnection.Connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement("SELECT * FROM users WHERE identification_number = ?");
        ps.setString(1, UserID);
        rs=ps.executeQuery();
        while(rs.next()) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/AccountInfoPage.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            AccountInfoController aic = loader.getController();
            String idn = rs.getString("identification_number");
            aic.setiud(idn);
            aic.setuname(rs.getString("name"));
            aic.setuaddress(rs.getString("address"));
            aic.setuemail(rs.getString("email"));
            aic.setuphone(rs.getString("phone"));
            aic.getUserID(UserID);
            aic.StqAccountInfo();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/AccountInfoPage.css");
            Image icon = new Image("/icons/usericon4.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("Account Information");
            stage.setScene(scene);
            stage.show();
        }
        ps.close();
        rs.close();



    }
    @FXML
    void ChangePassword(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ChangePassword.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        AccountInfoController aic = loader.getController();
        aic.getUserID(UserID);
        aic.StqPasswordChangePage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/ChangePassword.css");
        Image icon = new Image("/icons/LoginPage.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Change Password");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void BalanceTransfer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/BalanceTransfer.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        BalancePageController bpc = loader.getController();
        bpc.getUserID(UserID);
        bpc.StqBalanceTransPage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/BalanceTransfer.css");
        Image icon = new Image("/icons/DepositPage.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setTitle("Balance Transfer");
        stage.setScene(scene);
        stage.show();
    }




}







