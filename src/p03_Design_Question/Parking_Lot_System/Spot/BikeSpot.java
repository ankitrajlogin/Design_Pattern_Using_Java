package p03_Design_Question.Parking_Lot_System.Spot;

import p03_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p03_Design_Question.Parking_Lot_System.Basic.VehicleType;

public class BikeSpot extends ParkingSpot{
    public BikeSpot(int id){
        super(id) ;
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle){
        return (vehicle.getType() == VehicleType.BIKE) ;
    }
}
