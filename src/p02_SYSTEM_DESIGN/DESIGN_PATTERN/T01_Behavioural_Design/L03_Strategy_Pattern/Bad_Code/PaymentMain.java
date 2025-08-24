package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T01_Behavioural_Design.L03_Strategy_Pattern.Bad_Code;

/*
🔹 Strategy Pattern
📌 Definition

The Strategy Pattern is a behavioral design pattern that allows you to define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.

➡️ In simple words: Instead of writing multiple if-else statements for different behaviors, we encapsulate each behavior in its own class and switch them at runtime.

📌 Real-Life Example
Imagine you are making a Payment System for an e-commerce website.
A customer can pay using Credit Card, PayPal, or UPI.
Without Strategy Pattern → You’d probably write big if-else or switch-case in the PaymentService.
With Strategy Pattern → Each payment method is its own class (algorithm), and the user can select/change it at runtime.

🔹 When to Use Strategy Pattern?
When you have multiple ways of doing the same task (e.g., payment, sorting, navigation).
When you want to avoid huge if-else or switch blocks.
When you need to switch algorithms at runtime without changing client code.
 */

class PaymentService {
    public void pay(String method, double amount) {
        if (method.equals("CreditCard")) {
            System.out.println("Paid " + amount + " using Credit Card.");
        } else if (method.equals("PayPal")) {
            System.out.println("Paid " + amount + " using PayPal.");
        } else if (method.equals("UPI")) {
            System.out.println("Paid " + amount + " using UPI.");
        } else {
            System.out.println("Invalid payment method!");
        }
    }
}

public class PaymentMain {
    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.pay("CreditCard", 500);
        ps.pay("PayPal", 1000);
    }
}


/*
❌ Problems:
Violates Open-Closed Principle (OCP) → Adding a new method requires modifying pay().
Difficult to maintain when many payment methods exist.

Hard to extend (if you want a new strategy, you must modify the class → violates Open/Closed Principle).

 */


/*
Problem Before Strategy Pattern
Sometimes, you have a class that performs an action in multiple ways.
For example, in a Stock Market app (or Trading app), you might want different strategies to buy/sell stocks:
 */

