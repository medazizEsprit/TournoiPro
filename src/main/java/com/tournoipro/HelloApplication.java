package com.tournoipro;

import com.Entity.Equipe;
import com.Service.EquipeService;
import com.Utils.SwitchScenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("connexion.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Connexion");
//        stage.setScene(scene);
//        stage.show();
        SwitchScenes.getInstance().Switch("connexion",stage);
    }

    public static void main(String[] args) {
        launch();
    }
}