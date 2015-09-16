package org.hanze.control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.hanze.model.Stock;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Sander on 9/16/2015.
 */
public class TextController implements Observer, StockView {

    private static String VIEW_NAME = "Text";

    private HashMap<String, Label> labels;

    @FXML
    private Pane textPane;

    public TextController() {
        labels = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass() == Stock.class) {
            Stock stock = (Stock) o;
            String name = stock.getName();
            if (labels.get(name) == null) {
                labels.put(name, new Label());
                textPane.getChildren().add(labels.get(name));
            }
            labels.get(name).setText(name + " : " + stock.getPrice());
        }
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }
}
