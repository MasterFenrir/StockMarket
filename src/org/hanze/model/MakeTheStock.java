package org.hanze.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Wessel on 16-9-2015.
 * <p>
 * this class will generate stock fluctuation
 */
public class MakeTheStock implements Runnable {

    // The stock to fluctuate
    private Stock stock;
    // A boolean to stop the thread
    private boolean stopped = false;

    /**
     * Initialize this object with a Stock object to change in the thread
     *
     * @param stock
     */
    public MakeTheStock(Stock stock) {
        this.stock = stock;
    }

    /**
     * Stops the thread
     */
    public synchronized void stopThread() {
        this.stopped = true;
    }

    /**
     * The running method that continiously changes the price
     */
    @Override
    public void run() {
        while (!stopped) {//while not stopped keep running
            double randNum = (Math.random() * (.06)) - .03;//get a new random number between -0.03 and 0.03
            BigDecimal bd = new BigDecimal(stock.getPrice() + randNum);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            stock.setPrice(bd.doubleValue());//make our new price by adding randNum and rounding to 2 decimals
            try {
                Thread.sleep(2000);//wait 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
