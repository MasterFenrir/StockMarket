package org.hanze.model;

import java.text.DecimalFormat;
import java.util.Observable;

/**
 * Created by wessel on 16-9-2015.
 *
 * an observable class that represents a stock and that will start changing automatically when you call the run method.
 */
public class Stock extends Observable{
    private String name;
    private double price;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyObservers();//notify all  the observers
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.notifyObservers();//notify all  the observers
    }

    Stock(){
        super();
        this.name = "defaultStockName";
        this.price = 0.0;
    }

    Stock(String name, double price){
        super();
        this.name = name;
        this. price = price;
    }
}
