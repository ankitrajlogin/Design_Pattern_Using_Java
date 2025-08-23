package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L02_Observer_Pattern.Bad_Code;


/*
🔹 What is the Observer Pattern?
The Observer Pattern is a Behavioral Design Pattern.
It is used when we have a one-to-many dependency between objects.

👉 Meaning:
When one object (Subject/Observable) changes its state, all dependent objects (Observers) are notified automatically.

🔹 Real-Life Example
YouTube Channel (Subject) → When a creator uploads a new video, all subscribed users (Observers) are notified.
Stock Market (Subject) → When stock prices change, all traders (Observers) get the update.

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



class NewsAgency {
    private String news;

    public void setNews(String news) {
        this.news = news;
        // Directly notifying
        System.out.println("Notifying NewsChannel: " + news);
        System.out.println("Notifying NewsPaper: " + news);
    }
}


//Problems:
//Every time a new subscriber is added, we must modify NewsAgency.
//Violates Open/Closed Principle (OCP).

public class NewsChannelMain {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        agency.setNews("Observer Pattern Explained!");
    }
}
