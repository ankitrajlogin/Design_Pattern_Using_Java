package p04_Design_Question.Parking_Lot_System;

import p04_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p04_Design_Question.Parking_Lot_System.Spot.ParkingSpot;

import java.util.List;

public class ParkingFloor {
    private List<ParkingSpot> spots ;

    public ParkingFloor(List<ParkingSpot> spots){
        this.spots = spots ;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle){
        for(ParkingSpot spot : spots){
            if(!spot.isOccupied() && spot.canFitVehicle(vehicle)){
                return spot ;
            }
        }


        return null ;
    }


}
