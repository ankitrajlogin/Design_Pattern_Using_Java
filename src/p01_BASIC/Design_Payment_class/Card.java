package p01_BASIC.Design_Payment_class;



/**
 *
 * Abstract classes:
 * 1. Cannot be instantiated directly.
 * 2. Can contain both abstract methods (without a body) and normal methods (with a body).
 * 3. Must be extended by subclasses that provide implementations for all abstract methods.
 */


public abstract class Card implements PaymentMethod {
    private String cardNo ;
    private String userName ;

    public Card(String cardNo , String name){
        this.cardNo = cardNo ;
        this.userName = name ;
    }


    public String getCardNo(){
        return cardNo ;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * This method will define how payment is processed.
     ***/




}






