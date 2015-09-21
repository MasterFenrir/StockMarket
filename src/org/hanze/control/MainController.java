package org.hanze.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import org.hanze.model.MakeTheStock;
import org.hanze.model.Stock;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Sander on 9/16/2015.
 */
public class MainController implements Initializable {

    // The view files
    private static final String[] VIEW_FILES = {"../gui/GraphView.fxml", "../gui/TextView.fxml"};
    // The stock names
    private static final String[] STOCK_NAMES = {"IBM", "APPL", "RTPSN"};

    // The content pane
    @FXML
    private TabPane content;

    /**
     * Initialize the GUI
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Observer> views = new ArrayList<>();
        List<Stock> stocks = new ArrayList<>();
        List<MakeTheStock> stockMakers = new ArrayList<>();
        List<Observer> stockObservers = new ArrayList<>();
        double[] prices = {11.3, 44.4, 5.23};
        Stack<String> names = new Stack<>();
        names.add("IBM");
        names.add("APPL");
        names.add("RTPSN");

        Observer view;
        for (String e : VIEW_FILES) {
            view = tabLoader(e);
            if (view != null) views.add(view);
        }

        int i = 0;
        for (double price : prices) {
            stocks.add(new Stock(names.pop(), price));
            stockObservers.add(new StockObserver(views));
            stocks.get(i).addObserver(stockObservers.get(i));
            stockMakers.add(new MakeTheStock(stocks.get(i)));
            new Thread(stockMakers.get(i)).start();
            i++;
        }
    }

    private void createStocks(List<StockView> views) {

    }

    /**
     * Load views into tabs and return the observer
     *
     * @param view
     * @return
     */
    private Observer tabLoader(String view) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        try {
            Pane tab = loader.load();
            StockView cont = loader.<StockView>getController();
            String name = cont.getViewName();
            Tab nTab = new Tab(name, tab);
            content.getTabs().add(nTab);
            return (Observer) cont;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
