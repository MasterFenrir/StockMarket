package org.hanze.control;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by wessel on 16-9-2015.
 * <p>
 * Observes.
 */
public class StockObserver extends Observable implements Observer {

    /**
     * Add the observers
     *
     * @param views The observers
     */
    public StockObserver(List<Observer> views) {
        for (Observer view : views) {
            this.addObserver(view);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(o);
    }
}
