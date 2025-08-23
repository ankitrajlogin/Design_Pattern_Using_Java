package p01_BASIC.Design_Payment_class;
/*
Think of Payment as “a capability”, not “a type of thing”.
Being able to pay is a behavior.
- A class that can pay should just say: “I can pay”, without caring about its ancestry.
- That’s exactly what an interface is for — defining common behavior without forcing an inheritance chain.
 */

public interface PaymentMethod {
    void pay() ;
}
