package com.mycompany.atmmanagementsys.test;

import com.mycompany.atmmanagementsys.module1.controllers.AdminPageController;
import com.mycompany.atmmanagementsys.module1.controllers.UserPageController;
import com.mycompany.atmmanagementsys.utils.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField useridtf;
    @FXML
    private Label mssg;
    @FXML
    private Button loginb;
    @FXML
    private PasswordField passwordtf;
    @FXML
    private RadioButton userrb;
    @FXML
    private ToggleGroup UserOrAdminorEmp;
    @FXML
    private ToggleGroup UserOrAdmin;
    @FXML
    private RadioButton adminrb;
    @FXML
    private RadioButton emprb;
    @FXML
    private void Login(ActionEvent event) throws SQLException, IOException {
        double balance=0;
        double totale=1;
        double x1=0;
        double x2=0;
        Connection con = DbConnection.Connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (userrb.isSelected()) {
            ps = con.prepareStatement("SELECT * FROM users WHERE identification_number = ? and password = ?");
            ps.setString(1, useridtf.getText());
            ps.setString(2, passwordtf.getText());
            rs = ps.executeQuery();
            if (rs.next()) {
                  balance= rs.getDouble("balance");
                PreparedStatement ps1 = con.prepareStatement("SELECT * FROM comptes_stat WHERE id_user=?");
                ps1.setInt(1, rs.getInt("id"));
                ResultSet rs1 = ps1.executeQuery();

                if(rs1.next()){
                     totale=rs1.getDouble("totale");

                }

                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/UserPage.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                UserPageController upc = loader.getController();
                upc.GetUserID(useridtf.getText(), rs.getString("name"));
                x1=(balance/totale)*100;
                x2=100-x1;
                upc.SetpieChart(x1, x2);

                stage.setTitle("FXBank");
                Image icon = new Image("/icons/Logo.png");
                stage.getIcons().add(icon);
                stage.setMinHeight(710);
                stage.setMinWidth(1345);
                stage.setMaximized(true);
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/UserPage.css");
                stage.setScene(scene);
                stage.show();
                mssg.setText("");
                Media someSound = new Media(getClass().getResource("/audio/UserLogin.mp3").toString());
                MediaPlayer mp = new MediaPlayer(someSound);
                mp.play();
            }
            else{
            mssg.setText("Wrong Password Or UserID");
            }
            ps.close();
            rs.close();
        } else
            if (adminrb.isSelected()) {
            ps = con.prepareStatement("SELECT * FROM admins WHERE identification_number = ? and password = ?");
            ps.setString(1, useridtf.getText());
            ps.setString(2, passwordtf.getText());
            rs = ps.executeQuery();
            if(rs.next()) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/AdminPage.fxml"));
                loader.load();
                Parent root = loader.getRoot();
                AdminPageController apc = loader.getController();
                apc.GetAdminID( useridtf.getText());
                stage.setTitle("Admin Page");
                Image icon = new Image("/icons/Logo.png");
                stage.getIcons().add(icon);
                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/AdminPage.css");
                stage.setScene(scene);
                stage.show();
                mssg.setText("");
                Media someSound = new Media(getClass().getResource("/audio/AdminLogin.mp3").toString());
                MediaPlayer mp = new MediaPlayer(someSound);
                mp.play();
            }
            else{
            mssg.setText("Wrong Password Or AdminID");
            }
            ps.close();
            rs.close();
        }
       else{
                ps = con.prepareStatement("SELECT * FROM employee WHERE identification_number = ? and password = ?");
                ps.setString(1, useridtf.getText());
                ps.setString(2, passwordtf.getText());
                rs = ps.executeQuery();
                if(rs.next()) {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/AdminPage.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();
                    AdminPageController apc = loader.getController();
                    apc.GetAdminID( useridtf.getText());
                    stage.setTitle("Admin Page");
                    Image icon = new Image("/icons/Logo.png");
                    stage.getIcons().add(icon);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/styles/AdminPage.css");
                    stage.setScene(scene);
                    stage.show();
                    mssg.setText("");
                    Media someSound = new Media(getClass().getResource("/audio/AdminLogin.mp3").toString());
                    MediaPlayer mp = new MediaPlayer(someSound);
                    mp.play();
                }
                else{
                    mssg.setText("Wrong Password Or AdminID");
                }
                ps.close();
                rs.close();

            }

        con.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        userrb.setToggleGroup(group);
        adminrb.setToggleGroup(group);
        emprb.setToggleGroup(group);
//        // Load the background image
//        Image backgroundImage = new Image("file:/C:/Users/USER/Desktop/JavaFx projet/Bank-master/Images/image_back.jpg");
//
//        // Set the background image in the AnchorPane
//        anchorPane.setStyle("-fx-background-image: url('" + backgroundImage.getUrl() + "'); " +
//                "-fx-background-size: cover; " +
//                "-fx-background-repeat: no-repeat;");
//        // TODO
    }
    @FXML
    private void goToSignUp(@NotNull ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddCustomer.fxml"));
        Parent signUpRoot = loader.load();

        // You can get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Create a new scene
        Scene signUpScene = new Scene(signUpRoot);

        // Set the scene
        stage.setScene(signUpScene);
        stage.show();
    }
}
