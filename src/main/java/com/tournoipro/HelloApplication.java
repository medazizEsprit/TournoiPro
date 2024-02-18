package com.tournoipro;

import com.Entity.Equipe;
import com.Service.EquipeService;
import com.Utils.SwitchScenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
    Parent root = FXMLLoader.load(getClass().getResource("HomeJoueur.fxml"));
    Scene sc = new Scene(root);
    stage.setScene(sc);
    stage.show();

    //SwitchScenes.getInstance().Switch("ListEquipe",stage, new ListEquipeController(), "");
    }

    public static void main(String[] args) {

        launch();
    }
}