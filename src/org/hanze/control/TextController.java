package org.hanze.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.hanze.model.Stock;

import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Sander on 9/16/2015.
 */
public class TextController implements Observer, StockView, Initializable {

    private static String VIEW_NAME = "Text";

    private HashMap<String, Label> labels;

    @FXML
    private Pane textPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labels = new HashMap<>();
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        if (arg.getClass() == Stock.class) {
            Stock stock = (Stock) arg;
            String name = stock.getName();
            if (labels.get(name) == null) {
                labels.put(name, new Label());
                Platform.runLater(() -> textPane.getChildren().add(labels.get(name)));
            }
            Platform.runLater(() -> labels.get(name).setText(name + " : " + stock.getPrice()));
        }
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
