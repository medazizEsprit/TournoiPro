package com.tournoipro;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;



    public class MapController implements Initializable {
        @FXML
        WebView Map = new WebView();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            WebEngine webEngine = Map.getEngine();
            webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            Map.setPrefSize(800, 600);
            Platform.runLater(() -> {
                webEngine.load("https://maps.google.com");
            });


        }
    }
