package p01_BASIC.Design_Payment_class;

/*
What @Override actually does
It’s just an annotation — a kind of instruction to the compiler.

It tells the compiler:
  “This method is supposed to override a method from a superclass or implement an interface method.”
If your method doesn’t actually override anything (because of a typo, wrong parameters, wrong access modifier, etc.), the compiler will throw an error.
*/

public class CreditCard extends Card{
    // CreditCard constructor public so that code outside the package (or class) can create CreditCard objects.
    public CreditCard(String cardNo, String name) {
        super(cardNo, name);
    }



    public void pay() {
        System.out.println("Making payment using CreditCard") ;
    }
}
