package p02_SYSTEM_DESIGN.PROJECTS.P1_Ride_Sharing_App_Simple.V1;


import java.util.ArrayList;
import java.util.List;

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
}

class Driver {
    private String name;
    private Vehicle vehicle;
    Location location;

    public Driver(String name,Location location,Vehicle vehicle) {
        this.name = name;
        this.vehicle = vehicle;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
}

class Vehicle {

    String numberPlate;
    String type;

    public Vehicle(String numberPlate, String type) {
        this.numberPlate = numberPlate;
        this.type = type;
    }
}

class Passenger {
    String name;
    Location location;

    public Passenger(String name, Location location) {
        this.name = name;
        this.location = location;
    }
}



class RideSharingAppService {
    //Matching Service
    private List<Driver> drivers = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();

    //Methods to add Drivers and Passengers
    public void addDriver(Driver driver){
        drivers.add(driver);
    }
    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }

    //Booking Ride
    public void bookRide(Passenger passenger,double distance){
        //corner case
        if(drivers.isEmpty()){
            System.out.println("No drivers are available for " + passenger.name);
            return;
        }
        // Hard-coded assignment logic
        // find the nearest driver
        // O(N) Brute Force
        Driver assignedDriver = null;
        double minDistance = Double.MAX_VALUE;

        for(Driver driver:drivers){
            double currentDriverDistance = calcDistance(passenger.location,driver.location);
            if(currentDriverDistance<minDistance){
                minDistance = currentDriverDistance;
                assignedDriver = driver;
            }
        }
        //Fare Calculation
        double expectedFare = calcFare(assignedDriver.getVehicle(),distance);

        // Track the driver state - BOOKED/AVAILABLE

        // Show the driver and fare to the passenger
        System.out.println("Ride booked for "+passenger.name + " with driver " + assignedDriver.getName() +" for a fare of " + expectedFare);
        System.out.println("Driver is on the way " + assignedDriver.getName());
    }

    private double calcFare(Vehicle vehicle,double distance){
        if(vehicle.type.equals("Car")){
            return distance*20;
        }
        else if(vehicle.type.equals(10)){
            return distance*10;
        }
        else{
            return distance*8; //hard-code value
        }
    }

    private double calcDistance(Location one, Location two){
        //Euclidean Distance
        double dx = one.getLatitude() - two.getLatitude();
        double dy = one.getLongitude() - two.getLongitude();
        return Math.sqrt(dx*dx + dy*dy);
    }
}




public class Client {
    public static void main(String[] args) {
        Location loc1 = new Location(12.9716, 77.5946);  // Bangalore
        Location loc2 = new Location(12.9352, 77.6245);  // Near Bangalore
        Location loc3 = new Location(13.0352, 77.6175);  // Another place near Bangalore

        // Create Vehicles
        Vehicle car = new Vehicle("AB123CD", "Car");
        Vehicle bike = new Vehicle("XY987Z", "Bike");

        // Create Drivers
        Driver driver1 = new Driver("Alice", loc2, car);
        Driver driver2 = new Driver("Bob", loc3, bike);

        // Create Passengers
        Passenger passenger1 = new Passenger("John", loc1);
        Passenger passenger2 = new Passenger("Rahul", loc3);

        // Ride Sharing App
        RideSharingAppService app = new RideSharingAppService();
        app.addDriver(driver1);
        app.addDriver(driver2);
        app.addPassenger(passenger1);
        app.addPassenger(passenger2);

        //Book the Ride
        app.bookRide(passenger1,10);
        app.bookRide(passenger2,20);
        app.bookRide(passenger2,20);
    }
}