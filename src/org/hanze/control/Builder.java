package org.hanze.control;

import org.hanze.model.MakeTheStock;
import org.hanze.model.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wessel on 16-9-2015.
 *
 * builds the app
 */
public class Builder {
    public void build() {
        List<Stock> stocks = new ArrayList<>();
        List<MakeTheStock> stockMakers = new ArrayList<>();
        double[] prices = {11.3, 44.4, 5.23};
        Stack<String> names = new Stack<>();
        names.add("IBM");
        names.add("APPL");
        names.add("RTPSN");

        int i = 0;
        for(double price : prices){
            stocks.add(new Stock(names.pop(), price));
            stockMakers.add(new MakeTheStock(stocks.get(i)));
            new Thread(stockMakers.get(i)).run();
            i++;
        }
    }
}
