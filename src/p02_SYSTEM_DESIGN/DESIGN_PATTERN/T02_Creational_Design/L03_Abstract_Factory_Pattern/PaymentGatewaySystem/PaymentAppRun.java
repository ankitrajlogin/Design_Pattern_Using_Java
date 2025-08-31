package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L03_Abstract_Factory_Pattern.PaymentGatewaySystem;


/*
🔹 Problem Statement
- Suppose we are building a Payment Gateway System for an E-commerce app.
- Our app supports different payment methods (Credit Card, UPI).
- But payment processing varies depending on Payment Provider (PayPal, Razorpay, etc).
- For example:
    - PayPal should provide PayPalCreditCardProcessor and PayPalUPIProcessor.
    - Razorpay should provide RazorpayCreditCardProcessor and RazorpayUPIProcessor.

👉 We need a way to create families of related payment processors (CreditCard + UPI for each provider), without hardcoding them in client code.
 */

// Step 1: Define Abstract Products
interface CreditCardPayment{
    void processPayment() ;
}

interface UPIPayment{
    void processPayment() ;
}


// Step 2: Create Concrete Products (PayPal)
class PayPalCreditCard implements CreditCardPayment{
    public void processPayment(){
        System.out.println("Processing credit Card via Paypal");
    }
}

class PayPalUPI implements UPIPayment{
    public void processPayment(){
        System.out.println("Processing UPI via PayPal");
    }
}

// concrete products(Razorpay)
class RazorpayCreditCard implements CreditCardPayment {
    public void processPayment() {
        System.out.println("Processing Credit Card via Razorpay");
    }
}

class RazorpayUPI implements UPIPayment {
    public void processPayment() {
        System.out.println("Processing UPI via Razorpay");
    }
}


// step 3 : Abstract factory
interface PaymentFactory{
    CreditCardPayment createCreditCardPayment() ;
    UPIPayment createUPIPayment() ;
}

// step 4 : concrete Factories
class PayPalFactory implements PaymentFactory {
    public CreditCardPayment createCreditCardPayment() {
        return new PayPalCreditCard();
    }
    public UPIPayment createUPIPayment() {
        return new PayPalUPI();
    }
}

class RazorpayFactory implements PaymentFactory {
    public CreditCardPayment createCreditCardPayment() {
        return new RazorpayCreditCard();
    }
    public UPIPayment createUPIPayment() {
        return new RazorpayUPI();
    }
}


// step 5 : Client code
class PaymentApp{
    private CreditCardPayment card ;
    private  UPIPayment upi ;

    public PaymentApp(PaymentFactory factory){
        card = factory.createCreditCardPayment() ;
        upi = factory.createUPIPayment() ;
    }

    public void process(){
        card.processPayment();
        upi.processPayment();
    }
}



public class PaymentAppRun {
    public static void main(String[] args){
        PaymentFactory factory ;
        factory = new PayPalFactory() ;

        System.out.println("**********rendering the ui of paypal*********") ;
        PaymentApp app = new PaymentApp(factory) ;
        app.process() ;


        System.out.println("**********rendering the ui of razorpay*********") ;
        factory = new RazorpayFactory() ;
        app = new PaymentApp(factory) ;
        app.process();


    }



}
