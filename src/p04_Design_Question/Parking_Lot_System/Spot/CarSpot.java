package p04_Design_Question.Parking_Lot_System.Spot;

import p04_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p04_Design_Question.Parking_Lot_System.Basic.VehicleType;

public class CarSpot extends ParkingSpot{
    public CarSpot(int id){
        super(id) ;
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle){
        return (vehicle.getType() == VehicleType.CAR) || (vehicle.getType() == VehicleType.BIKE) ;
    }
}
