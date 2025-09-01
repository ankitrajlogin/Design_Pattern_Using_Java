package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T03_Structural_Design.L01_Adapter_Pattern.Good_Code;


/*
Components of Adapter Pattern
    - Target Interface → What the client expects (PaymentProcessor).
    - Adaptee → Existing incompatible class (PayPal).
    - Adapter → Bridge between Target and Adaptee (PayPalAdapter).
    - Client → Uses the Target Interface without caring about implementation.
 */

/*
Steps to Implement Adapter Pattern
1.    Identify Target Interface (Client’s expectation)
       Example: PaymentProcessor with pay(amount).

2.    Identify Adaptee (Incompatible class)
        Example: PayPal with makePayment(money).

3.    Create Adapter class
        - Implements Target Interface (PaymentProcessor).
        - Holds a reference to Adaptee (PayPal).
        - Translates method calls (pay → makePayment).

4.     Use Adapter in Client Code
         Client uses only the target interface, unaware of the adaptee.

*/


/*
Related Terms
    - Wrapper → Adapter is often called a Wrapper since it wraps an incompatible class.
    - Facade vs Adapter → Facade provides a new simplified interface, while Adapter makes an existing incompatible interface usable.
    - Decorator vs Adapter → Decorator adds new behavior, Adapter changes interface.

 */

// Target Interface
interface PaymentProcessor{
    void pay(int amount) ;
}

// Existing implementation
class CreditCardPayment implements PaymentProcessor{
    public void pay(int amount){
        System.out.println("Paid " + amount + " using Credit Card Card") ;
    }
}

// Third-party library (unchangeable)
class PayPal{
    public void makePayment(int money){
        System.out.println("Paid " + money + " using PayPal");
    }
}

// Adapter : converts paypal's interface into paymentProcessor
class PayPalAdapter implements PaymentProcessor{
    private PayPal paypal ;

    public PayPalAdapter(){
        this.paypal = new PayPal() ;
    }

    @Override
    public void pay(int amount){
        paypal.makePayment(amount);  // Delegates call to paypal
    }
}

public class AdapterPatternExample {
    public static void main(String[] args){
        PaymentProcessor cc = new CreditCardPayment() ;
        cc.pay(100) ;

        PaymentProcessor paypal = new PayPalAdapter() ;
        paypal.pay(200) ;

    }
}





/*
Real-World Examples
    - Mobile Charger Adapter (AC power → USB power).
    - Legacy System Integration → New system expects JSON, old one provides XML → Adapter converts XML → JSON.
    - Java I/O Streams → InputStreamReader acts as an adapter between InputStream (byte) and Reader (char).
 */