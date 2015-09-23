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
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private List<MakeTheStock> stockMakers;

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
        stockMakers = new ArrayList<>();
        Observer view;
        for (String e : VIEW_FILES) {
            view = tabLoader(e);
            if (view != null) views.add(view);
        }
        createStocks(views);
    }

    /**
     * Stop the stockmakers
     */
    public void stopStockMakers() {
        for (MakeTheStock e : stockMakers) {
            e.stopThread();
        }
    }

    /**
     * Create the stocks and start the fluctuation
     *
     * @param views
     */
    private void createStocks(List<Observer> views) {
        for (String e : STOCK_NAMES) {
            Stock stock = new Stock(e, getRandomPrice(10));
            StockObserver obs = new StockObserver(views);
            stock.addObserver(obs);
            MakeTheStock maker = new MakeTheStock(stock);
            stockMakers.add(maker);
            new Thread(maker).start();
        }
    }

    /**
     * Get a random price
     *
     * @param upperBound The upperbound
     * @return
     */
    private double getRandomPrice(int upperBound) {
        Random rand = new Random();
        BigDecimal bd = new BigDecimal(rand.nextInt(upperBound) + rand.nextDouble());
        return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
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