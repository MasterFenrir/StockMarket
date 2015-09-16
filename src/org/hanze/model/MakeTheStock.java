package org.hanze.model;

import java.text.DecimalFormat;

/**
 * Created by wessel on 16-9-2015.
 *
 * this thing will generate stock fuctuation
 */
public class MakeTheStock implements Runnable{
    private Stock stock;
    private boolean stopped = false;

    private MakeTheStock(){}

    public MakeTheStock(Stock stock){
        this.stock = stock;
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
            stock.setPrice(Double.valueOf(df.format(stock.getPrice() + randNum)));//make our new price by adding randNum and rounding to 2 decimals
            try {
                Thread.sleep(2000);//wait 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //were done
    }
}
