package p04_Design_Question.Parking_Lot_System.PriceCalculation;

public class CarPricingStrategy
        implements PricingStrategy {

    public double calculateFee(long hours) {
        return hours * 20;
    }
}