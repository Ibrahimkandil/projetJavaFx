/*
MIT License
Copyright (c) 2018 Guru
Only for Educational purposes and for reference.
*/
package com.mycompany.atmmanagementsys.test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        Image icon = new Image("/icons/Logo.png");



        stage.getIcons().add(icon);
        stage.setResizable(true);


        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        Platform.runLater(() -> {
            Media someSound = new Media(getClass().getResource("/audio/Welcome.mp3").toString());
            MediaPlayer mp = new MediaPlayer(someSound);
            mp.play();
        });
    }
    public static void main(String[] args) {
        launch(args);
    }

}
