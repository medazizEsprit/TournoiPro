package com.Utils;

import com.tournoipro.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScenes {
    static SwitchScenes switchScenes;
    public static SwitchScenes getInstance(){
        if (switchScenes == null)
            switchScenes = new SwitchScenes();
        return switchScenes;
    }
    public SwitchScenes(){}
    public void Switch(String fxml, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(fxml);
        stage.setScene(scene);
        stage.show();
    }
}
