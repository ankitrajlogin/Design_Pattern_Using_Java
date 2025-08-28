package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L06_Iterator_Pattern.NotificationSystem;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.Queue;



/*
📌 Question / Requirement
- We want to design a notification system that can handle different types of notifications (like Push Notifications and SMS Notifications) in a uniform way.
- Each notification type may use a different data structure:
    - Push notifications → stored in a Set (no duplicates, insertion order preserved).
    - SMS notifications → stored in a Queue (FIFO order).

The challenge:
👉 Even though these collections are different (Set vs Queue), we want a common way to traverse (iterate) through them.

This is where the Iterator Pattern comes in:
    - It allows us to access elements of a collection sequentially without exposing the underlying data structure.
    - Client code (like Main) doesn’t care whether notifications are stored in a Set or Queue. It just asks for an Iterator and loops over it.
*/


/*
📌 Approach (Iterator Pattern)
1. Define a common interface NotificationCollection → forces all collections to provide createIterator().
2. Create a Notification class → represents each notification with a message.
3. PushNotification class → stores notifications in a LinkedHashSet, provides its own iterator.
4. SMSNotification class → stores notifications in a Queue, provides its own iterator.
5. Main class → client code that adds notifications and prints them, without knowing the internal collection type.

 */



interface NotificationIterator<T> {
    boolean hasNext();
    T next();
}


interface NotificationCollection {
    NotificationIterator<Notification> createIterator();
}


// Notification.java
class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}



// Collection of Push Notifications
class PushNotification implements NotificationCollection {

    private Set<Notification> pushNotifications;

    public PushNotification() {
        pushNotifications = new LinkedHashSet<>();
    }

    public void addNotification(String message) {
        pushNotifications.add(new Notification(message));
    }

    @Override
    public NotificationIterator<Notification> createIterator() {
        return new PushNotificationIterator(pushNotifications.toArray(new Notification[0]));
    }
    
}

class PushNotificationIterator implements NotificationIterator<Notification> {
    private Notification[] notifications;
    private int index = 0;

    public PushNotificationIterator(Notification[] notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean hasNext() {
        return index < notifications.length;
    }

    @Override
    public Notification next() {
        return notifications[index++];
    }
}



// Collection of SMS Notifications
class SMSNotification implements NotificationCollection {

    private Queue<Notification> smsNotifications;

    public SMSNotification() {
        smsNotifications = new ArrayDeque<>();
    }

    public void addNotification(String message) {
        smsNotifications.add(new Notification(message));
    }

    @Override
    public NotificationIterator<Notification> createIterator() {
        return new SMSNotificationIterator(smsNotifications.toArray(new Notification[0]));
    }

    // Custom NotificationIterator for SMS Notifications
    private class SMSNotificationIterator implements NotificationIterator<Notification> {
        private Notification[] notifications;
        private int index = 0;

        public SMSNotificationIterator(Notification[] notifications) {
            this.notifications = notifications;
        }

        @Override
        public boolean hasNext() {
            return index < notifications.length;
        }

        @Override
        public Notification next() {
            return notifications[index++];
        }
    }
}




public class NotificationMain {
    public static void main(String[] args) {
        // Create collections
        PushNotification pushNotification = new PushNotification();
        SMSNotification smsNotification = new SMSNotification();

        // Add push notifications
        pushNotification.addNotification("Push: New friend request");
        pushNotification.addNotification("Push: App update available");
        pushNotification.addNotification("Push: Discount on shopping");

        // Add SMS notifications
        smsNotification.addNotification("SMS: OTP 1234");
        smsNotification.addNotification("SMS: Your recharge is successful");
        smsNotification.addNotification("SMS: Low balance alert");

        // Iterate over Push Notifications
        System.out.println("🔔 Push Notifications:");
        printNotifications(pushNotification.createIterator());

        // Iterate over SMS Notifications
        System.out.println("\n📱 SMS Notifications:");
        printNotifications(smsNotification.createIterator());
    }

    // Helper method to print notifications
    private static void printNotifications(NotificationIterator<Notification> iterator) {
        while (iterator.hasNext()) {
            Notification n = iterator.next();
            System.out.println(n.getMessage());
        }
    }
}





