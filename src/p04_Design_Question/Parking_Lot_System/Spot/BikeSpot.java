package p04_Design_Question.Parking_Lot_System.Spot;

import p04_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p04_Design_Question.Parking_Lot_System.Basic.VehicleType;

public class BikeSpot extends ParkingSpot{
    public BikeSpot(int id){
        super(id) ;
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle){
        return (vehicle.getType() == VehicleType.BIKE) ;
    }
}
