package p03_Design_Pattern_Implementation.Payment_Template_pattern;

/*
Online Payment Flow

Common Steps:
    - Validate user
    - Process payment
    - Send receipt

But:
    - UPI payment differs
    - Card payment differs
    - PayPal differs

 */

abstract class PaymentProcessor{
    public final void pay(){
        validateUser() ;

        processPayment() ;

        sendReceipt() ;
    }

    private void validateUser(){
        System.out.println("User validated");
    }

    private void sendReceipt(){
        System.out.println("Receipt send");
    }

    protected abstract void processPayment() ;
}

class UPIPayment extends PaymentProcessor{

    @Override
    protected void processPayment(){
        System.out.println("UPI Payment processed");
    }
}

class CardPayment extends PaymentProcessor {

    @Override
    protected void processPayment() {
        System.out.println("Card payment processed");
    }
}

public class PaymentMain {

    public static void main(String[] args){
        PaymentProcessor upiPayment = new UPIPayment() ;
        upiPayment.pay() ;

        System.out.println() ;

        PaymentProcessor cardPayment = new CardPayment() ;
        cardPayment.pay() ;

    }





}
