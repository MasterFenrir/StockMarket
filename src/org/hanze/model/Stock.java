package org.hanze.model;

import java.util.Observable;

/**
 * Created by Wessel on 16-9-2015.
 * <p>
 * An observable class that represents a stock and that will start changing automatically when you call the run method.
 */
public class Stock extends Observable {
    // Name
    private String name;
    // The value
    private double price;

    /**
     * Construct a stock with a given name and price
     *
     * @param name  The name of the stock
     * @param price The price of the stock
     */
    public Stock(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    /**
     * Get the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
        this.setChanged();
        this.notifyObservers();//notify all  the observers
    }

    /**
     * Get the price
     *
     * @return The price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Change the price of the stock
     *
     * @param price The new price
     */
    public void setPrice(double price) {
        this.price = price;
        this.setChanged();
        this.notifyObservers();//notify all  the observers
    }


}
