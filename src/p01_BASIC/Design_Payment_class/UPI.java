package p01_BASIC.Design_Payment_class;

public class UPI implements PaymentMethod {
    private String upiId;

    UPI(String id){
        this.upiId = id ;
    }

    public String getUpiId() {
        return upiId;
    }


    @Override
    public void pay() {
        System.out.println("Making payment using UPI with ID: " + upiId);
    }



}
