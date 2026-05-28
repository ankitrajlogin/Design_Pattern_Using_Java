package p02_SYSTEM_DESIGN.L02_DESIGN_PATTERN.T01_Behavioural_Design.L03_Strategy_Pattern.Good_Code;

/*
🔹 Components of Strategy Pattern
- Strategy (Interface) → Common interface for all algorithms.
- Concrete Strategies (Classes) → Actual implementations of the algorithms.
- Context (Main Class / Client) → Uses a Strategy reference to execute the algorithm dynamically.

 */

/*

📌 Strategy Pattern Solution
Instead of hardcoding behavior inside one class, we define a family of strategies (algorithms), and make them interchangeable.

🔑 Steps:
1. Create an interface (Strategy) – defines the common behavior (e.g., trade()).
2. Implement concrete strategies – each class has its own version of trade().
3. Use composition – the Trader class gets a strategy injected, and it can switch strategies at runtime.
 */


// Strategy
interface PaymentStrategy{
    void pay(double amount) ;
}


// concrete strategies

class CreditCardPayment implements PaymentStrategy{
    public void pay(double amount){
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }
}


// context (Uses Strategy)
class PaymentContext{
    private PaymentStrategy strategy ;

    // Constructor injection Or Setter injection
    public void setPaymentStrategy(PaymentStrategy strategy){
        this.strategy = strategy ;
    }

    public void pay(double amount){
        strategy.pay(amount) ;
    }
}


public class paymentMain {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // pay using Credit card
        context.setPaymentStrategy(new CreditCardPayment());
        context.pay(1000);

        // Example: pay using PayPal
        context.setPaymentStrategy(new PayPalPayment());
        context.pay(500);

        // Example: pay using UPI
        context.setPaymentStrategy(new UpiPayment());
        context.pay(200);
    }
}


/*
Benefits:
Easily extendable → Just add new PaymentStrategy class, no need to modify existing code.
Clean & maintainable → Removes if-else clutter.
Flexible → Strategy can be changed at runtime.


🔹 Related Terms
Context → The class that uses a Strategy (e.g., PaymentContext).
Strategy Interface → Defines a common behavior (e.g., PaymentStrategy).
Concrete Strategy → Actual implementation (e.g., PayPalPayment).
Dynamic Behavior → You can change strategy at runtime.

 */