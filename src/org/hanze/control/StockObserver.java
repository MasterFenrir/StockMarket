package org.hanze.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by wessel on 16-9-2015.
 *
 * its our observer
 */
public class StockObserver extends Observable implements Observer{
    List<Observer> views = new ArrayList<>();

    public StockObserver(List<Observer> views){
        this.views = views;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.notifyObservers(o);
    }
}
