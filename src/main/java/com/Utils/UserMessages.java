package com.Utils;

import javafx.scene.control.Alert;

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
    public void Information (String title,String header,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
