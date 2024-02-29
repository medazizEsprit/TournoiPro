package com.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class UserMessages {
    static UserMessages userMessages;
    public static UserMessages getInstance(){
        if (userMessages == null)
            userMessages = new UserMessages();
        return userMessages;
    }
    public void Error (String title,String header,String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    public void Warning (String title,String header,String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
    public Optional<ButtonType> Information (String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        return alert.showAndWait();
    }
    public boolean Confirmation (String title,String header,String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            return true;
        else return false;
    }
}
