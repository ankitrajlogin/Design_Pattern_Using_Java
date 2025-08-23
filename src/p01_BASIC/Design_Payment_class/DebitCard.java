package p01_BASIC.Design_Payment_class;

public class DebitCard extends Card {
    public DebitCard(String cardNo, String name) {
        super(cardNo, name);
    }

    @Override
    public void pay() {
        System.out.println("Making payment using CreditCard") ;
    }
}
