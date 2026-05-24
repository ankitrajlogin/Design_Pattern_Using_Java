package p03_Design_Question.Parking_Lot_System;

import p03_Design_Question.Parking_Lot_System.Basic.Bike;
import p03_Design_Question.Parking_Lot_System.Basic.Car;
import p03_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p03_Design_Question.Parking_Lot_System.Spot.BikeSpot;
import p03_Design_Question.Parking_Lot_System.Spot.CarSpot;
import p03_Design_Question.Parking_Lot_System.Spot.ParkingSpot;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        // Create Spots
        List<ParkingSpot> spots =
                new ArrayList<>();

        spots.add(new CarSpot(1));
        spots.add(new CarSpot(2));
        spots.add(new BikeSpot(3));

        // Create Floor
        ParkingFloor floor =
                new ParkingFloor(spots);

        // Create Parking Lot
        List<ParkingFloor> floors =
                new ArrayList<>();

        floors.add(floor);

        ParkingLot parkingLot =
                new ParkingLot(floors);

        // Create Vehicle
        Vehicle car =
                new Car("KA01AB1234");

        // Park Vehicle
        Ticket ticket =
                parkingLot.parkVehicle(car);

        if(ticket != null) {

            System.out.println(
                    "Vehicle Parked Successfully\n"
            );

            ticket.printTicket();

        } else {

            System.out.println(
                    "No Parking Spot Available"
            );
        }

        // Simulate Parking Duration
        Thread.sleep(5000);

        // Exit Vehicle
        parkingLot.exitVehicle(ticket);
    }
}