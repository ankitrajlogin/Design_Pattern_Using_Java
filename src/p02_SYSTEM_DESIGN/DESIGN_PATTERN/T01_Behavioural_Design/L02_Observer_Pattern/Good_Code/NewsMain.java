package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L02_Observer_Pattern.Good_Code;


import java.util.ArrayList;
import java.util.List;

/*
🔹 Components of Observer Pattern
1. Subject (Observable)
Holds a list of observers.
Notifies observers about changes.

2. Observer
Interface or abstract class that defines the update() method.
All concrete observers implement this.

3. Concrete Subject
The actual object being observed.

4. Concrete Observer
Objects that need updates when the subject changes.
 */



/*
📌 Observer Pattern – What We’re Doing
📝 Problem it Solves

Imagine a stock market 📈.
-> When the price of a stock changes, you don’t want only one person to know.
-> Instead, all subscribed investors (observers) should automatically get notified.

So, we need a system where one subject (stock) automatically notifies multiple observers (investors) whenever its state changes.

🛠️ What We Do in Observer Pattern

1. Subject (Publisher) → The main object whose state changes.
-> Example: Stock class.
-> It keeps a list of observers.
-> When the state (price) changes, it notifies all observers.

2. Observer (Subscriber) → Objects that want to be updated when subject changes.
-> Example: Investor class.
-> They subscribe/unsubscribe to the subject.
-> When notified, they react accordingly (e.g., buy/sell stock).

3. Decoupling → Subject doesn’t need to know details of observers.
-> It only calls a method like update(price).
-> This makes the system flexible (we can add/remove observers anytime).

⚡ In Simple Words
Think of YouTube 📺:
-> A channel is the Subject.
-> Subscribers are the Observers.
-> When the channel uploads a new video (state change), all subscribers get notified automatically.

 */




// Observer Interface
interface Observer{
    void update(String news) ;
}

// Subject Interface
interface Subject{
    void addObserver(Observer o) ;
    void removeObserver(Observer o) ;
    void notifyObservers() ;
}

// Concrete Subject
class NewsAgency implements Subject{
    private String news ;
    private List<Observer> observers = new ArrayList<>() ;

    public void setNews(String news){
        this.news = news ;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o){
        observers.add(o) ;
    }

    @Override
    public void removeObserver(Observer o){
        observers.remove(o) ;
    }

    @Override
    public void notifyObservers(){
        for(Observer o : observers){
            o.update(news) ;
        }
    }
}

// Concrete Observers
class NewsChannel implements Observer{
    private String channelName ;

    public NewsChannel(String name){
        this.channelName = name ;
    }

    @Override
    public void update(String news) {
        System.out.println(channelName + " received news: " + news) ;
    }
}

class NewsPaper implements Observer {
    @Override
    public void update(String news) {
        System.out.println("Newspaper printed news: " + news);
    }
}


public class NewsMain {
    public static void main(String[] args){
        NewsAgency agency = new NewsAgency() ;

        Observer channel1 = new NewsChannel("CNN") ;
        Observer channel2 = new NewsChannel("BBC") ;
        Observer paper = new NewsPaper() ;

        agency.addObserver(channel1);
        agency.addObserver(channel2);
        agency.addObserver(paper);

        agency.setNews("Observer Pattern implemented in Java !");

    }
}

/*
🔹 Benefits of Observer Pattern
✅ Loose coupling between Subject and Observers
✅ Easy to add/remove observers (extensibility)
✅ Reusability of components
✅ Widely used in event-driven systems (GUIs, stock tickers, notification systems)

⚡ Quick Recap:
Observer Pattern = Publisher-Subscriber Model.
Subject (Publisher) notifies all Observers (Subscribers) automatically.

 */