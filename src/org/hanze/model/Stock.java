package org.hanze.model;

import java.text.DecimalFormat;
import java.util.Observable;

/**
 * Created by wessel on 16-9-2015.
 *
 * an observable class that represents a stock and that will start changing automatically when you call the run method.
 */
public class Stock extends Observable implements Runnable{
    private String name;
    private double price;
    private boolean stopped = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    /**
     * stops the thread
     */
    public synchronized void stopThread(){
        this.stopped = true;
    }

    /**
     * the running method that continiously changes the price
     */
    @Override
    public void run() {
        while (!stopped) {//while not stopped keep running

            double randNum = (Math.random() * (.06)) - .03;//get a new random number between -0.03 and 0.03
            DecimalFormat df = new DecimalFormat("#.##");//get a decimal format that rounds to 2 decimals
            this.price = Double.valueOf(df.format(price + randNum));//make our new price by adding randNum and rounding to 2 decimals
            this.notifyObservers();//notify all  the observers
            try {
                Thread.sleep(2000);//wait 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //were done
    }
}
