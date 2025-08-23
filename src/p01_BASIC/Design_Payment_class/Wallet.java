package p01_BASIC.Design_Payment_class;

public class Wallet implements PaymentMethod {

    private String WalletId  ;

    @Override
    public void pay() {
        System.out.println("Making payment via wallet") ;
    }
}
