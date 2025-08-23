package p02_SYSTEM_DESIGN.Solid_principle.L04_OCP_Good_Code;



// Abstraction
interface PaymentMethod {
    void pay(double amount);
}

// Concrete Implementations
class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

// High-level class (depends on abstraction, not concrete)
class PaymentProcessor {
    public void processPayment(PaymentMethod method, double amount) {
        method.pay(amount);
    }
}


public class OCP_Good_Code {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Credit Card Payment
        PaymentMethod cc = new CreditCardPayment();
        processor.processPayment(cc, 2000.0);

        // PayPal Payment
        PaymentMethod paypal = new PayPalPayment();
        processor.processPayment(paypal, 5000.0);

        // Tomorrow: Add UPI (without changing PaymentProcessor)
        PaymentMethod upi = new UPIPayment();
        processor.processPayment(upi, 1000.0);
    }
}


// New feature added later (no modification needed in PaymentProcessor)
class UPIPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}


/*
Key Points to Remember about OCP
Open for Extension → Add new behavior (e.g., UPI, Crypto payments).
Closed for Modification → No need to touch existing PaymentProcessor.
Achieved using interfaces, abstract classes, strategy pattern, dependency injection.
Protects tested code from breaking due to new features.

🟢 Real-World Analogy
Think of a power socket:
- You don’t redesign the socket every time you buy a new appliance.
- Instead, appliances (extensions) just implement the plug standard.
- The socket (processor) remains closed for modification but open for extension with new devices.

 */


//*******************************************************************
//*******************************************************************
//NOTE :

/*

1️⃣ PaymentMethod cc = new CreditCardPayment();
👉 Here, the reference type is the interface (PaymentMethod).
👉 The object type is the concrete class (CreditCardPayment).

You can only call methods defined in the interface (PaymentMethod).
This allows polymorphism → the same variable can point to different implementations.

PaymentMethod cc = new CreditCardPayment();
cc.pay(1000); // ✅ Allowed (defined in PaymentMethod)
// cc.someCreditCardSpecificMethod(); ❌ Not allowed

📌 Advantage → flexibility:
Later you can write:
cc = new PayPalPayment(); // still works
Because both CreditCardPayment and PayPalPayment implement PaymentMethod.



2️⃣ CreditCardPayment cc = new CreditCardPayment();
👉 Here, the reference type is the concrete class.
👉 The object type is also the concrete class.

You can call all methods from CreditCardPayment (including any extra methods not in the interface).
But you lose polymorphism (can’t assign a PayPalPayment to this reference).

CreditCardPayment cc = new CreditCardPayment();
cc.pay(1000); // ✅ Allowed
// If CreditCardPayment had a unique method:
cc.validateCardNumber(); // ✅ Allowed (but not in PaymentMethod)
// cc = new PayPalPayment(); ❌ Compile error

📌 Advantage → You can access class-specific methods.
📌 Disadvantage → You lose flexibility and tie your code to one class.

 */

