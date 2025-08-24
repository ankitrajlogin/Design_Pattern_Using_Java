package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L02_Observer_Pattern.InvestorAlertSystemExcersise;

import java.util.*;


//Stock Price Monitoring System Application Exercise
//You are developing a stock price monitoring system that implements the Observer Design Pattern to notify investors (observers) whenever there is a significant change in stock prices (subject). The system should allow multiple investors to track different stocks and get notified about price fluctuations.


interface Observer{
    void update(String stockSymbol , double oldPrice , double newPrice)  ;
}

class InvestorA implements Observer{
    private String name ;

    public InvestorA(String name){
        this.name = name ;
    }

    @Override
    public void update(String stockSymbol, double oldPrice, double newPrice) {
        System.out.println(name + " notified: " + stockSymbol + " price changed from "
                + oldPrice + " to " + newPrice);
    }
}

class InvestorB implements Observer {
    private String name;

    public InvestorB(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double oldPrice, double newPrice) {
        System.out.println(name + " notified: " + stockSymbol + " price changed from "
                + oldPrice + " to " + newPrice);
    }
}



class StockMarket {
    private Map<String, Double> stockPrices = new HashMap<>();
    private Map<String, Double> thresholds = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String stockSymbol, double oldPrice, double newPrice) {
        for (Observer observer : observers) {
            observer.update(stockSymbol, oldPrice, newPrice);
        }
    }

    public void addStock(String symbol, double price, double thresholdPercent) {
        stockPrices.put(symbol, price);
        thresholds.put(symbol, thresholdPercent);
    }

    public void setStockPrice(String symbol, double newPrice) {
        double oldPrice = stockPrices.get(symbol);
        double threshold = thresholds.get(symbol);

        double percentageChange = Math.abs((newPrice - oldPrice) / oldPrice) * 100;

        if (percentageChange >= threshold) {
            stockPrices.put(symbol, newPrice);
            notifyObservers(symbol, oldPrice, newPrice);
        } else {
            stockPrices.put(symbol, newPrice); // update silently
        }
    }
}



public class InvestorAlertSystem {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        // Create observers
        InvestorA investorA = new InvestorA("Investor A");
        InvestorB investorB = new InvestorB("Investor B");

        // Register observers
        stockMarket.registerObserver(investorA);
        stockMarket.registerObserver(investorB);

        // Add stock with 5% threshold
        stockMarket.addStock("AAPL", 100.0, 5.0);

        // Trigger changes
        stockMarket.setStockPrice("AAPL", 102.0);  // No notification (< 5%)
        stockMarket.setStockPrice("AAPL", 108.0);  // Notification (≥ 5%)

        // Remove InvestorB
        stockMarket.removeObserver(investorB);

        stockMarket.setStockPrice("AAPL", 120.0);  // Only InvestorA gets notified
    }
}
