package p04_Design_Question.Parking_Lot_System;

import p04_Design_Question.Parking_Lot_System.Basic.Vehicle;
import p04_Design_Question.Parking_Lot_System.Spot.ParkingSpot;

import java.util.List;
import java.util.UUID;

public class ParkingLot {

    private List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {

        this.floors = floors;
    }

    public Ticket parkVehicle(Vehicle vehicle) {

        for(ParkingFloor floor : floors) {

            ParkingSpot spot =
                    floor.findAvailableSpot(vehicle);

            if(spot != null) {

                spot.parkVehicle(vehicle);

                Ticket ticket =
                        new Ticket(
                                UUID.randomUUID().toString(),
                                vehicle,
                                spot
                        );

                return ticket;
            }
        }

        return null;
    }

    public void exitVehicle(Ticket ticket) {

        ParkingSpot spot = ticket.getSpot();

        long exitTime =
                System.currentTimeMillis();

        long duration =
                (exitTime -
                        ticket.getEntryTime())
                        / 1000;

        double amount = calculateFee(duration);

        System.out.println(
                "\nVehicle Returning..."
        );

        System.out.println(
                "Vehicle Number : " +
                        ticket.getVehicle()
                                .getVehicleNumber()
        );

        System.out.println(
                "Vehicle was parked at Spot : "
                        + spot.getSpotId()
        );

        System.out.println(
                "Parking Fee : Rs " + amount
        );

        Payment payment =
                new Payment();

        payment.makePayment(amount);

        spot.removeVehicle();

        System.out.println(
                "Spot is now free"
        );
    }

    private double calculateFee(long seconds) {

        return seconds * 2;
    }
}
