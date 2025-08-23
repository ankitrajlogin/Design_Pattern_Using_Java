package p02_SYSTEM_DESIGN.Solid_principle.L03_OCP_Bad_Code;

/*
🟠 Open/Closed Principle (OCP)
👉 Definition:
Software entities (classes, modules, functions) should be open for extension, but closed for modification.

That means:
You should be able to add new behavior (extension) without changing existing code (modification).
Achieved using abstraction + polymorphism.

🔴 Why OCP is Important?
Prevents breaking existing code when new requirements arrive.
Promotes scalability → system grows without rewriting old logic.
Reduces bugs → tested code stays untouched.
Encourages polymorphism and interfaces/abstract classes.
 */

class PaymentProcessor {
    public void processPayment(String paymentMethod, double amount) {
        if (paymentMethod.equals("CreditCard")) {
            System.out.println("Processing Credit Card Payment of " + amount);
        } else if (paymentMethod.equals("PayPal")) {
            System.out.println("Processing PayPal Payment of " + amount);
        }
        else{
            System.out.println("This Payment method not available") ;
        }
    }
}

/*
Problems:
Adding new payment types (UPI, Crypto, etc.) requires modifying this class.
Every time we add a new condition → code grows, becomes harder to maintain.
Violates OCP because class is not closed for modification.

 */


public class OCP_Bad_Code {

}
