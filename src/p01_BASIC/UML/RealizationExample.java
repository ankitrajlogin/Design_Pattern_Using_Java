package p01_BASIC.UML;

/*
Realization in UML
Definition:
Realization is a relationship between an interface and a class (or component) that implements it.
It shows that the class "realizes" (implements) the behavior defined by the interface.
Diagram Notation:
        In UML, Realization is shown as a dashed line with a hollow triangle pointing from the implementing class → interface.

Example:
[Class] ----▷ [Interface]

Key Points about Realization
One-to-one or one-to-many:
A class can implement one or multiple interfaces.

Defines contract:
The interface defines what should be done (method signatures), and the class defines how it will be done.

Supports abstraction:
Allows coding to an interface instead of coding to implementation.

 */


// Interface = contract
interface PaymentMethod {
    void pay(double amount);
}

// Class realizing (implementing) the interface
class CreditCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Another class realizing the interface
class PayPal implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

public class RealizationExample {
    public static void main(String[] args) {
        PaymentMethod pm1 = new CreditCard();
        pm1.pay(250.0);

        PaymentMethod pm2 = new PayPal();
        pm2.pay(500.0);
    }
}



/*
UML Diagram

«interface»
PaymentMethod
          + pay(amount: double)

                ▲ (dashed line with hollow triangle)
        |
        ------------------------------
        |                            |
CreditCard                 PayPal
+ pay(amount: double)      + pay(amount: double)


✅ Summary:
Dependency = “uses temporarily”
Association = “has a relationship”
Aggregation = “has-a (independent whole-part)”
Composition = “has-a (dependent whole-part)”
Inheritance = “is-a (class hierarchy)”
Realization = “implements (interface → class)”
 */