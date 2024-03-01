package com.tournoipro;

import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Map11Controller implements Initializable {
    @javafx.fxml.FXML
    private javafx.scene.web.WebView WebView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = WebView.getEngine();
        webEngine.load("www.facebook.com");

    }
}
