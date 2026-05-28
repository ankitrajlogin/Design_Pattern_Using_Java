package p03_Design_Pattern_Implementation.Design_Payment_class;

public class Wallet implements PaymentMethod {

    private String WalletId  ;

    @Override
    public void pay() {
        System.out.println("Making payment via wallet") ;
    }
}
