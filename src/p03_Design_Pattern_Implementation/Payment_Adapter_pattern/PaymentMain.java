package p03_Design_Pattern_Implementation.Payment_Adapter_pattern;


// Existing third party class( adaptee)
class StripeGateway{
    public void makeTransaction(double money){
        System.out.println("Stripe transaction completed: " + money);
    }
}

// Juspay payment third party class ( adaptee)
class JuspayGateway{
    public void doTransaction(String money){
        System.out.println("Juspay transaction completed : " + money);
    }
}

interface PaymentProcessor{
    void processPayment(int amount) ;
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;

    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(int amount) {
        // converting request
        stripeGateway.makeTransaction(amount);
    }
}

class JuspayAdapter implements PaymentProcessor{
    private JuspayGateway juspayGateway ;

    public JuspayAdapter(JuspayGateway juspayGateway){
        this.juspayGateway = juspayGateway  ;
    }

    @Override
    public void processPayment(int amount){
        String amt = Integer.toString(amount) ;
        juspayGateway.doTransaction(amt);
    }
}

class CheckoutService {

    private PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(int amount) {
        paymentProcessor.processPayment(amount);
    }
}

public class PaymentMain {
    public static void main(String[] args) {
        StripeGateway stripeGateway = new StripeGateway();
        JuspayGateway juspayGateway = new JuspayGateway();

        PaymentProcessor adapter1 = new StripeAdapter(stripeGateway);
        PaymentProcessor adapter2 = new JuspayAdapter(juspayGateway);

        CheckoutService service = new CheckoutService(adapter1);
        service.checkout(500);

        service = new CheckoutService(adapter2) ;
        service.checkout(1000);
    }
}
