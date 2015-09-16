package org.hanze.control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Sander on 9/16/2015.
 */
public class MainController implements Initializable {

    private static final String[] VIEW_FILES = {"../gui/GraphView.fxml", "../gui/TextView.fxml"};

    @FXML
    private TabPane content;

    public MainController() {

    }

    /**
     * Initialize the GUI
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Observer> observers = new ArrayList<>();
        Observer obs;
        for (String e : VIEW_FILES) {
            obs = tabLoader(e);
            if (obs != null) observers.add(obs);
        }
        Builder builder = new Builder();
        builder.build();
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
