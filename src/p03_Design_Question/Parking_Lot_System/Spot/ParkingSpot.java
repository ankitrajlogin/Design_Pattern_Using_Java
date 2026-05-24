package p03_Design_Question.Parking_Lot_System.Spot;

import p03_Design_Question.Parking_Lot_System.Basic.Vehicle;


//If a class has at least one abstract method,
//then the class must also be marked as abstract.

public abstract class ParkingSpot {
    protected int spotId ;
    protected boolean occupied ;
    protected Vehicle vehicle ;

    public ParkingSpot(int spotId){
        this.spotId = spotId ;
    }

    public abstract boolean canFitVehicle(Vehicle vehicle) ;

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle ;
        occupied = true ;
    }

    public void removeVehicle(){
        vehicle = null ;
        occupied = false ;
    }

    public boolean isOccupied(){
        return occupied ;
    }

    public int getSpotId() {
        return spotId;
    }



}
