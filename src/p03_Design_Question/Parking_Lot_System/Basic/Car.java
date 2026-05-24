package p03_Design_Question.Parking_Lot_System.Basic;

import p03_Design_Question.Parking_Lot_System.Basic.Vehicle;

public class Car extends Vehicle {
    public Car(String number) {
        super(number, VehicleType.CAR);
    }
}
