package com.Utils;

import com.Entity.Stade;
import com.tournoipro.HelloApplication;
import com.tournoipro.ModifierStadeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class SwitchScenes {
    public static Stage stage;
    static SwitchScenes switchScenes;
    public static SwitchScenes getInstance(){
        if (switchScenes == null)
            switchScenes = new SwitchScenes();
        return switchScenes;
    }
    public static Stage getStage() {
        return stage;
    }
    public void Switch(String fxml, Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(fxml);
        stage.setScene(scene);
        stage.show();
    }

    public void Switch(String fxml, Stage stage, Object object, String cssFile) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        String css = object.getClass().getResource(cssFile + ".css").toExternalForm();
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(css);
        stage.setTitle(fxml);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToUpdateStade(String fxml, Stage stage, int id) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(fxml);
        stage.setScene(scene);
        ModifierStadeController modifierStadeController = fxmlLoader.getController();
        modifierStadeController.setId(id);
        stage.show();
    }

    public void Switch(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(fxml);
        stage.setScene(scene);
        stage.show();
    }
}
