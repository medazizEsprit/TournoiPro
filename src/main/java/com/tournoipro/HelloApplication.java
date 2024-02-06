package com.tournoipro;

import com.Entity.Equipe;
import com.Service.EquipeService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        Equipe equipe = new Equipe(2,"Test",10);
        EquipeService es = new EquipeService();
        es.ajout(equipe);
    }

    public static void main(String[] args) {
        launch();
    }
}