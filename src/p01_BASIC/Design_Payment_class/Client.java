package p01_BASIC.Design_Payment_class;

public class Client {
    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.addPaymentMethod("AnkitDebitCard", new DebitCard("1234", "Ankit Raj debit card")) ;
        ps.addPaymentMethod("AnkitCreditCard" , new DebitCard("4222" , "ankit raj credit card")) ;
        ps.addPaymentMethod("AnkitRajUpi" , new UPI("ankitraj@oksbi"));

        ps.addPaymentMethod("AnkitRajWallet" , new Wallet()) ;

        ps.makePayment("AnkitRajUpi");
        ps.makePayment("AnkitCreditCard");
        ps.makePayment("AnkitDebitCard");
        ps.makePayment("AnkitRajWallet") ;
    }
}
