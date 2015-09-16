package org.hanze.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Sander on 9/16/2015.
 */
public class MainController implements Initializable {

    @FXML
    private TabPane content;

    public MainController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        content.getTabs();
        System.out.println("Hello!");
        Builder builder = new Builder();
        builder.build();
    }
}
