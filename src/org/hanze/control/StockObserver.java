package org.hanze.control;

import org.hanze.model.Stock;

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

    public StockObserver(List<Observer> views){
        for(Observer view : views){
            this.addObserver(view);
//            System.out.println(((StockView)view).getViewName());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setChanged();
//        System.out.println("A Observer has observed!");
        this.notifyObservers(o);
    }
}
