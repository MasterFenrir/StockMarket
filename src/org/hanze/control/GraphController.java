package org.hanze.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.hanze.model.Stock;

import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Sander on 9/16/2015.
 */
public class GraphController implements Observer, StockView, Initializable {

    private static String VIEW_NAME = "Graph";

    @FXML
    private LineChart stockLineChart;
    private HashMap<String, XYChart.Series<Number, Number>> series;
    private int counter = 1;

    public GraphController() {
        series = new HashMap<>();
    }

    @Override
    public void update(Observable o, Object arg) {
        String name = ((Stock) arg).getName();
        Double price = ((Stock) arg).getPrice();
        if (series.get(name) == null) {
            XYChart.Series<Number, Number> serie = new XYChart.Series<Number, Number>();
            serie.setName(name);
            series.put(name, serie);
            Platform.runLater(() -> stockLineChart.getData().add(serie));
        }
        Platform.runLater(() -> series.get(name).getData().add(new XYChart.Data(counter, price)));
        counter++;
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //series.getData().add(new XYChart.Data<>(1, 23.0));
//        series.getData().add(new XYChart.Data(2, 14));
        //stockLineChart.getData().add(series);
    }
}
