package p04_Design_Question.Parking_Lot_System;

import p04_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p04_Design_Question.Parking_Lot_System.Spot.ParkingSpot;

public class Ticket {

    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot spot;
    private long entryTime;

    public Ticket(String ticketId,
                  Vehicle vehicle,
                  ParkingSpot spot) {

        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spot = spot;

        this.entryTime =
                System.currentTimeMillis();
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void printTicket() {

        System.out.println("------ TICKET ------");

        System.out.println(
                "Ticket ID : " + ticketId
        );

        System.out.println(
                "Vehicle No : " +
                        vehicle.getVehicleNumber()
        );

        System.out.println(
                "Spot ID : " +
                        spot.getSpotId()
        );

        System.out.println("--------------------");
    }
}