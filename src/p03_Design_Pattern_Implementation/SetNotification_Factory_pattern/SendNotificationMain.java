package p03_Design_Pattern_Implementation.SetNotification_Factory_pattern;

interface Notification{
    void send() ;
}
class EmailNotification implements Notification {

    @Override
    public void send() {

        System.out.println("Sending Email Notification");
    }
}

class SMSNotification implements Notification {

    @Override
    public void send() {

        System.out.println("Sending SMS Notification");
    }
}

class PushNotification implements Notification {

    @Override
    public void send() {

        System.out.println("Sending Push Notification");
    }
}

class NotificationFactory {

    public Notification createNotification(String type) {

        if(type.equalsIgnoreCase("EMAIL")) {

            return new EmailNotification();
        }

        else if(type.equalsIgnoreCase("SMS")) {

            return new SMSNotification();
        }

        else if(type.equalsIgnoreCase("PUSH")) {

            return new PushNotification();
        }

        return null;
    }
}

public class SendNotificationMain {
    public static void main(String[] args) {

        NotificationFactory factory =
                new NotificationFactory();

        Notification notification1 =
                factory.createNotification("EMAIL");

        notification1.send();

        Notification notification2 =
                factory.createNotification("SMS");

        notification2.send();
    }
}
