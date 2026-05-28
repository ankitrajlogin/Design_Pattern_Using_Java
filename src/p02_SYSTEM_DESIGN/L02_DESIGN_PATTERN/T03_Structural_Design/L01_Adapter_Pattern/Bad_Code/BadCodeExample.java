package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T03_Structural_Design.L01_Adapter_Pattern.Bad_Code;



/*
🔹 1. What is the Adapter Pattern?
Definition: Adapter Pattern is a structural design pattern that allows objects with incompatible interfaces to work together.

Think of it as a translator between two systems that cannot directly communicate.

Real life analogy:
👉 You have a laptop charger with a 2-pin plug, but your hotel only has a 3-pin socket. You use a travel adapter.
The adapter doesn’t change your charger or the socket – it just makes them compatible.
 */


/*
Problem Statement
    - Suppose we are building a payment system.
    - Our application expects a PaymentProcessor interface.
    - But we need to integrate with different 3rd-party payment gateways (like PayPal, Stripe, Razorpay).

Their APIs don’t match our interface.
 */


// Existing Interface
interface PaymentProcessor {
    void pay(int amount);
}

// Existing implementation
class CreditCardPayment implements PaymentProcessor {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

// Third-party library (we cannot modify this code)
class PayPal {
    public void makePayment(int money) {
        System.out.println("Paid " + money + " using PayPal");
    }
}

// Client Code (Bad way of handling PayPal)
public class BadCodeExample {
    public static void main(String[] args) {
        PaymentProcessor cc = new CreditCardPayment();
        cc.pay(100);

        // Directly using PayPal (breaking abstraction)
        PayPal paypal = new PayPal();
        paypal.makePayment(200);  // Not consistent with PaymentProcessor
    }
}


/*
❌ Problems with this bad code:
1. Breaks abstraction → Client has to know about two different APIs (pay() vs makePayment()).
2. Tight coupling → If tomorrow you add another payment provider, you must again write separate code.
3. No polymorphism → You can’t treat PayPal like a PaymentProcessor.
4. Scalability issue → Harder to integrate new payment systems.
 */