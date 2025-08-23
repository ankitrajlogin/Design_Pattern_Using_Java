package p01_BASIC.Design_Payment_class;

import java.util.HashMap;

public class PaymentService
{
//    Instance Variable
    HashMap<String , PaymentMethod> paymentMethods ;

    public PaymentService() {
        paymentMethods = new HashMap<>();
    }

    public void addPaymentMethod(String name , PaymentMethod pm){
        paymentMethods.put(name , pm) ;
    }

    public void makePayment(String name){
        PaymentMethod pm = paymentMethods.get(name) ;
        if (pm == null) {
            System.out.println("Payment method '" + name + "' not found.");
            return;
        }
        pm.pay() ; // Run time Polymorphism
    }
}
