package p04_Design_Question.Parking_Lot_System.PriceCalculation;

interface PricingStrategy {

    double calculateFee(long hours);
}