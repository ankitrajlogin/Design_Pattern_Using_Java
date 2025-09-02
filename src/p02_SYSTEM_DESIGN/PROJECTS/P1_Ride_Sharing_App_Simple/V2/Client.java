package p02_SYSTEM_DESIGN.PROJECTS.P1_Ride_Sharing_App_Simple.V2;


import java.util.ArrayList;
import java.util.List;

//*************************************************
// LOCATION
//*************************************************
class Location {
    private double latitude;
    private double longitude;
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double calcDistance(Location two){
        //Euclidean Distance
        double dx = this.getLatitude() - two.getLatitude();
        double dy = this.getLongitude() - two.getLongitude();
        return Math.sqrt(dx*dx + dy*dy);
    }
}

//*************************************************
// USER : deriver and passenger both extend user
//*************************************************

class User{
    protected String name ;
    protected  String email ;
    protected Location location ;

    public User(String name , String email , Location location){
        this.name = name ;
        this.name = name ;
        this.location = location ;
    }

    // Setter
    public Location getLocation(){
        return location ;
    }

    public void setLocation(Location location){
        this.location = location ;
    }
}

class Passenger extends User{
    public Passenger(String name, String email, Location location) {
        super(name, email, location);
    }
    //any other methods??
    public void notify(String msg){
        System.out.println("passenger : " + msg);
    }
}


class Driver extends User{
    private Vehicle vehicle;
    public Driver(String name, String email, Location location, Vehicle vehicle) {
        super(name, email, location);
        this.vehicle = vehicle;
    }
    public Vehicle getVehicle(){
        return vehicle;
    }
    //Any other methods??
    public void notify(String msg){
        System.out.println("Driver " + msg);
    }
}

//*************************************************
// Vehicle : driver have vehicle different type
//*************************************************

abstract class Vehicle {
    protected String numberPlate;

    public Vehicle(String numberPlate){
        this.numberPlate = numberPlate;
    }
    //Fare Calc
    public abstract double getFarePerKm();
}

class Car extends Vehicle {
    public Car(String numberPlate) {
        super(numberPlate);
    }

    @Override
    public double getFarePerKm() {
        return 20;
    }
}

class Bike extends Vehicle {
    public Bike(String numberPlate) {
        super(numberPlate);
    }

    @Override
    public double getFarePerKm() {
        return 10;
    }
}


//*************************************************
// Fare Strategy calculation
//*************************************************

interface FareStrategy {
    double calcFare(Vehicle vehicle,double distance);
}

class StandardFareStrategy implements FareStrategy{

    @Override
    public double calcFare(Vehicle vehicle, double distance) {
        return vehicle.getFarePerKm()*distance;
    }
}

class SharedFareStrategy implements FareStrategy{

    @Override
    public double calcFare(Vehicle vehicle, double distance) {
        return vehicle.getFarePerKm()*distance*.50;
    }
}

class LuxuryStrategy implements FareStrategy{

    @Override
    public double calcFare(Vehicle vehicle, double distance) {
        //additional 50% surcharge for luxury ride
        return vehicle.getFarePerKm()*distance*1.5;
    }
}

//*************************************************
// Ride Matching system
//*************************************************
class RideMatchingSystem{
    private List<Driver> availableDrivers = new ArrayList<>() ;

    public void addDriver(Driver driver){
        availableDrivers.add(driver) ;
    }

    public void requestRide(Passenger passenger,double distance,FareStrategy fareStrategy){
        //base case
        if(availableDrivers.isEmpty()){
            //mechanism to notify the passenger
            passenger.notify("No drivers are unavailable");
            return;
        }
        //find the nearest driver available
        Driver nearestDriver = findNearestDriver(passenger.getLocation());

        //Mediator
        availableDrivers.remove(nearestDriver);

        // passenger.notify("Ride schedule successfully" + nearestDriver);
        Ride ride = new Ride(passenger,nearestDriver,distance,fareStrategy);
        //Calc fare
        ride.calculateFare();


        passenger.notify("Ride schedued with fare + Rs"+ride.getFare());
        nearestDriver.notify("You have a new ride request for "+ride.getFare());


        //Change the Status of the Ride
        ride.updateStatus(RideStatus.ONGOING);

        //Change the status of ride after ride is finished
        ride.updateStatus(RideStatus.COMPLETED);
        availableDrivers.add(nearestDriver);
        return;
    }

    private Driver findNearestDriver(Location passengerLocation){
        Driver assignedDriver = null;
        double minDist = Double.MAX_VALUE;

        for(Driver driver:availableDrivers){
            double distance = driver.getLocation().calcDistance(passengerLocation);
            if(distance<minDist){
                minDist = distance;
                assignedDriver = driver;
            }
        }
        return assignedDriver;
    }
}


//********************************************
//Ride -> calculate fare , update status , notify users , etc function .
//********************************************

enum RideStatus{
    SCHEDULED, ONGOING, COMPLETED;
}

class Ride {
    private Passenger passenger;
    private Driver driver;
    private double distance;
    private FareStrategy fareStrategy;
    private double fare;
    private RideStatus status;

    public Ride(Passenger passenger,Driver driver, double distance, FareStrategy fareStrategy){
        this.passenger = passenger;
        this.driver = driver;
        this.distance = distance;
        this.fareStrategy = fareStrategy;
        this.status = RideStatus.SCHEDULED;
    }

    public void calculateFare(){
        this.fare = fareStrategy.calcFare(driver.getVehicle(), distance);
    }

    public void updateStatus(RideStatus status){
        this.status = status;
        notifyUsers(status);
    }
    private void notifyUsers(RideStatus status){
        passenger.notify("Your ride is "+status);
        driver.notify("Ride Status : "+status);
    }

    public double getFare(){
        return fare;
    }
}

public class Client {
    public static void main(String[] args) {
        Location loc1 = new Location(12.9716, 77.5946);  // Bangalore
        Location loc2 = new Location(12.9352, 77.6245);  // Near Bangalore
        Location loc3 = new Location(13.0352, 77.6175);  // Another place near Bangalore

        Vehicle car = new Car("AB123CD");
        Vehicle bike = new Bike("XY987Z");

        Driver driver1 = new Driver("Alice", "alice@rideshare.com", loc2, car);
        Driver driver2 = new Driver("Bob", "bob@rideshare.com", loc3, bike);

        Passenger passenger1 = new Passenger("John", "john@rideshare.com", loc1);


        RideMatchingSystem rideMatchingSystem = new RideMatchingSystem();
        rideMatchingSystem.addDriver(driver1);
        rideMatchingSystem.addDriver(driver2);

        rideMatchingSystem.requestRide(passenger1,10,new StandardFareStrategy());


    }
}