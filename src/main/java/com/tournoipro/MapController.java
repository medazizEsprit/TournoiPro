package com.tournoipro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    @FXML
    WebView Map = new WebView() ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = Map.getEngine();

        webEngine.load("https://maps.google.com");
    }
}
