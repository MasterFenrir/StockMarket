package org.hanze.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import org.hanze.model.Stock;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sander on 9/16/2015.
 * A controller to control a graph
 */
public class GraphController implements Observer, StockView, Initializable {

    // The name of the view
    private static String VIEW_NAME = "Graph";

    // The graph
    @FXML
    private LineChart stockLineChart;
    // The data points
    private HashMap<String, XYChart.Series<String, Number>> series;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        series = new HashMap<>();

        // Zoom in and out
        stockLineChart.setOnScroll(event -> {
            event.consume();
            if (event.getDeltaY() == 0) {
                return;
            }
            double scaleFactor = (event.getDeltaY() > 0) ? 1.1 : 1 / 1.1;

            stockLineChart.setScaleX(stockLineChart.getScaleX() * scaleFactor);
            stockLineChart.setScaleY(stockLineChart.getScaleY() * scaleFactor);
        });

        // Reset
        stockLineChart.setOnMousePressed(event -> {
            if (event.getClickCount() == 2) {
                stockLineChart.setScaleX(1.0);
                stockLineChart.setScaleY(1.0);
            }
        });
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        String name = ((Stock) arg).getName();
        Double price = ((Stock) arg).getPrice();
        if (series.get(name) == null) {
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName(name);
            series.put(name, serie);
            Platform.runLater(() -> stockLineChart.getData().add(serie));
        }
        Platform.runLater(() -> series.get(name).getData().add(new XYChart.Data(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()), price)));
        if (series.get(name).getData().size() > 10)
            Platform.runLater(() -> series.get(name).getData().remove(0));
    }

    @Override
    public String getViewName() {
        return VIEW_NAME;
    }

}
